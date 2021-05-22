package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String address;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    UserEntity user;

    public AddressEntity(String address, UserEntity user) {
        this.address = address;
        this.user = user;
    }
}

