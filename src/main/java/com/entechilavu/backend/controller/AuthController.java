package com.entechilavu.backend.controller;


import com.entechilavu.backend.dto.AuthResponse;
import com.entechilavu.backend.dto.LoginRequest;
import com.entechilavu.backend.dto.RegisterRequest;
import com.entechilavu.backend.security.User;
import com.entechilavu.backend.service.impl.AuthService;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/auth/register")
    public ResponseEntity<String> regitser(@RequestBody RegisterRequest registerRequest) {
        User user = authService.createUser(registerRequest);
        return ResponseEntity.ok()
                .body(user.getUsername() + " created");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("controller");
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
