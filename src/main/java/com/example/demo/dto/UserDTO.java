package com.example.demo.dto;

import com.example.demo.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    String name;

    Set<AddressDTO> addresses;

    public UserDTO(@NotBlank String name, Set<AddressDTO> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public UserDTO(UserEntity user) {
        this.name = user.getName();
        this.addresses = user.getAddresses().stream().map(p -> {
            AddressDTO addressDTO = new AddressDTO(p.getAddress());
            addressDTO.setUserId(user.getId());
            return addressDTO;
        }).collect(Collectors.toSet());
    }
}
