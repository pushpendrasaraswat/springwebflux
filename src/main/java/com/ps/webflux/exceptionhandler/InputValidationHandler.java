package com.ps.webflux.exceptionhandler;

import com.ps.webflux.dto.InputFailedValidationExceptionResponse;
import com.ps.webflux.exception.InputValidationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationExceptionResponse> handleValidationException(InputValidationException ex) {
        InputFailedValidationExceptionResponse response = new InputFailedValidationExceptionResponse();
        response.setInput(ex.getInput());
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMesg());

        return ResponseEntity.badRequest().body(response);
    }
}
