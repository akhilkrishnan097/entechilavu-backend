package com.entechilavu.backend.dto;

public record ErrorResponseDto(String statusCode, String message, String error) {
}
