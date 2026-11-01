package com.entechilavu.backend.service.impl;

import com.entechilavu.backend.dto.AuthResponse;
import com.entechilavu.backend.dto.LoginRequest;
import com.entechilavu.backend.dto.RegisterRequest;
import com.entechilavu.backend.security.User;

public interface AuthService {
    User createUser(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);
}
