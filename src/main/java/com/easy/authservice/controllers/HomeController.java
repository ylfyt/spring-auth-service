package com.easy.authservice.controllers;

import com.easy.authservice.dtos.ResponseDto;
import com.easy.authservice.dtos.user.DataUser;
import com.easy.authservice.middlewares.Authorization;
import com.easy.authservice.repositories.HomeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeRepository _homeRepository;

    public HomeController(HomeRepository homeRepository) {
        _homeRepository = homeRepository;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("ping")
    @Authorization
    public String ping() {
        return "pong";
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDto<String>> test() {
        try {
            _homeRepository.execSleep();
        } catch (Exception e) {
            // System.out.println(e.toString());
        }
        return  ResponseEntity.ok(new ResponseDto<>("hello"));
    }
}
