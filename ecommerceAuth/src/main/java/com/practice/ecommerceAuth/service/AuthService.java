package com.practice.ecommerceAuth.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.ecommerceAuth.entity.UserEntity;
import com.practice.ecommerceAuth.repository.UserRepository;
import com.practice.ecommerceAuth.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User already exists!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return "User register successfully!";
    }

    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return jwtUtil.generateToken(authentication);
    }

}
