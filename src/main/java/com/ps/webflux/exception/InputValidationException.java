package com.ps.webflux.exception;

import reactor.core.publisher.Mono;

public class InputValidationException extends RuntimeException{

    private static final String MESG="Allowed range is between 10 to 20";
    private static final int ERROR_CODE=100;
    private final int input;

    public InputValidationException(int input){
        this.input=input;
    }

    public static int getErrorCode(){
        return ERROR_CODE;
    }
    public static String getMesg(){
        return MESG;
    }

    public int getInput() {
        return input;
    }

}
