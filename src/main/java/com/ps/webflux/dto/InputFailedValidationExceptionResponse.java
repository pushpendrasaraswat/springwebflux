package com.ps.webflux.dto;

import lombok.Data;

@Data
public class InputFailedValidationExceptionResponse {
    private int errorCode;
    private int input;
    private String message;

}
