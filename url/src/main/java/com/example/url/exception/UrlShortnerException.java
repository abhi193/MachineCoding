package com.example.url.exception;

import lombok.Getter;

@Getter
public class UrlShortnerException extends RuntimeException{

    private final String message;
    private final int code;
    private final String url;

    public UrlShortnerException(String message, int code, String url){
        super(message);
        this.message=message;
        this.code=code;
        this.url=url;
    }


}
