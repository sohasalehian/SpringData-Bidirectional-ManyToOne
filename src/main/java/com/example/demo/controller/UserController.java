package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.AddressEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserController(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser (@RequestBody UserDTO user) {
        UserEntity newUser = new UserEntity(user.getName());
        userRepository.saveAndFlush(newUser);

        Set<AddressEntity> addresses = user.getAddresses().stream().map(p -> {
            AddressEntity newAddress = new AddressEntity(p.getAddress(), newUser);
            addressRepository.save(newAddress);
            return newAddress;
        }).collect(Collectors.toSet());

        newUser.setAddresses(addresses);
        userRepository.saveAndFlush(newUser);

        return ResponseEntity.ok(new UserDTO(newUser));
//        return ResponseEntity.ok(newUser);

//        UserEntity gotUser = userRepository.findById(newUser.getId())
//                .orElseThrow(() -> new RuntimeException("Error: fetching new user from database"));
//        return ResponseEntity.ok(gotUser);
    }

//    h.sadeghian@rightel.ir
    //postman attached
    //fetch
    // save user -> address are saved and fetched
}
