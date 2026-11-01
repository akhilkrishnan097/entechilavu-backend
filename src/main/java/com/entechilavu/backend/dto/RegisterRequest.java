package com.entechilavu.backend.dto;




public record RegisterRequest(

        String name,
        String email,
        String password,
        String role) {
}
