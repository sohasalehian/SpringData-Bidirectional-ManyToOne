package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {
    @NotBlank
    String address;

    Long userId;

    public AddressDTO(@NotBlank String address) {
        this.address = address;
    }
}
