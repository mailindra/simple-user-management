package com.mailindra.demo.simpleUserManagement.infrastructure.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String status;
    private int code;
    private String message;

}
