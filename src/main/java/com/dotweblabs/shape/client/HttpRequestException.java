package com.dotweblabs.shape.client;

public class HttpRequestException extends Exception {
    private int code;
    public HttpRequestException(String message, int code){
        super(message);
        this.code = code;
    }
    public HttpRequestException() {
    }

    public int getCode() {
        return code;
    }
}
