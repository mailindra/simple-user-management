package com.mailindra.demo.simpleUserManagement.infrastructure.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        int code = 30000;
        String message = e.getMessage();

        return new ResponseEntity<>(
                new ErrorResponse(status.getReasonPhrase(), code, message ),
                status
        );
    }
}
