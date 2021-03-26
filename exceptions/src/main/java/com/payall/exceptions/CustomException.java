package com.payall.exceptions;

public class CustomException extends RuntimeException{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomException(String message){
        super();
        this.message = message;
    }
}
