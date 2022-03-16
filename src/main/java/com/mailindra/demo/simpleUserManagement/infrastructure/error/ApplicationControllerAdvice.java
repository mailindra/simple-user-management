package com.mailindra.demo.simpleUserManagement.infrastructure.error;

import com.mailindra.demo.simpleUserManagement.exception.ConflictBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleResourceNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        log.error(ex.getLocalizedMessage());
        int code = 30000;
        String message = ex.getMessage();
        return new ErrorResponse("NOT_FOUND", code, message );

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleValidationError(BindException ex, HttpServletRequest request) {
        log.error(ex.getLocalizedMessage());
        int code = 30001;
        FieldError error = ex.getFieldError();
        String message = "Invalid value for field "+error.getField()+", rejected value:"+ error.getRejectedValue();
        return new ErrorResponse("BAD_REQUEST", code, message );

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception ex, HttpServletRequest request) {
        log.error(ex.getLocalizedMessage());
        int code = 30001;
        String message = ex.getLocalizedMessage();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), code, message );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(ConflictBusinessException.class)
    public ErrorResponse handleGeneralException(ConflictBusinessException ex, HttpServletRequest request) {
        log.error(ex.getMessage());
        int code = 30002;
        String message = ex.getMessage();
        return new ErrorResponse("CONFLICT", code, message );
    }
}
