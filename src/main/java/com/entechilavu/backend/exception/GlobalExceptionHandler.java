package com.entechilavu.backend.exception;


import com.entechilavu.backend.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(UserAlreadyExistsException e) {
        ErrorResponseDto responseDto = new ErrorResponseDto("404",e.getMessage(),e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseDto);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }


}
