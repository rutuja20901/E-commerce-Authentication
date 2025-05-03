package com.practice.ecommerceAuth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecommerceAuth.entity.UserEntity;
import com.practice.ecommerceAuth.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {
        return authService.register(user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user) {
        return authService.login(user.getUsername(), user.getPassword());
    }

}
