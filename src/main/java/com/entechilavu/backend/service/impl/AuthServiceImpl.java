package com.entechilavu.backend.service.impl;


import com.entechilavu.backend.dto.AuthResponse;
import com.entechilavu.backend.dto.LoginRequest;
import com.entechilavu.backend.dto.RegisterRequest;
import com.entechilavu.backend.exception.UserAlreadyExistsException;
import com.entechilavu.backend.repo.UserRepository;
import com.entechilavu.backend.security.JwtTokenUtil;
import com.entechilavu.backend.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public User createUser(RegisterRequest registerRequest) {

        boolean userExists = userRepository.existsByEmail(registerRequest.email());
        if (userExists) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole("NORMAL_USER");
        user.setCreated(LocalDate.now());

        return userRepository.save(user);

    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {



        // 1. Authenticate email and password
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.username(),
                                loginRequest.password()
                        )
                );

        // 2. Get authenticated username
        String email = authentication.getName();

        // 3. Fetch user details
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        // 4. Generate JWT
        String token = jwtTokenUtil.generateToken(authentication.getName());

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getUsername()
        );
    }
}
