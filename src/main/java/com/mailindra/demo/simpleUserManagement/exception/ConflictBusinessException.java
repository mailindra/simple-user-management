package com.mailindra.demo.simpleUserManagement.exception;

public class ConflictBusinessException extends RuntimeException{
    private String status;
    private int code;
    private String message;

    public ConflictBusinessException(String message){
        super(message);
    }

    public ConflictBusinessException(String status, int code, String message){
        super(message);
        this.status=status;
        this.code=code;
        this.message=message;
    }

}
