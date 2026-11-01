package com.entechilavu.backend.dto;

public record AuthResponse(String token,
                           String email,
                           String name) {
}
