package com.example.Exceptions;

public class MyException extends Exception{
    private String errorCode = "Unknown_Exception";

    public MyException(String message, String errorCode) {
        super(message);

        this.errorCode=errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
