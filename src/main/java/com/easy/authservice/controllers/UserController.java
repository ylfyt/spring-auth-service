package com.easy.authservice.controllers;

import com.easy.authservice.dtos.user.RegisterInputDto;
import com.easy.authservice.models.User;
import com.easy.authservice.repositories.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody RegisterInputDto data){

        var exist = userRepository.findByUsername(data.username);
        if (exist != null) return ResponseEntity.badRequest().body("");

        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var hashedPassword =  bCryptPasswordEncoder.encode(data.password);

        var user = new User(data.username, hashedPassword);
        userRepository.save(user);



        return ResponseEntity.ok(user);
    }
}
