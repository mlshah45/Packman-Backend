package com.packman.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by mlshah on 12/6/15.
 */
public class ErrorInfo {

    private HttpStatus status;
    private int code;
    private String message;
    private String developerMessage;
    private String moreInfoUrl;
    @JsonIgnore
    private Throwable throwable;

    public ErrorInfo() {}

    public ErrorInfo(HttpStatus status, int code, String message, String developerMessage, String moreInfoUrl, Throwable throwable) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.moreInfoUrl = moreInfoUrl;
        this.throwable = throwable;
    }

    public ErrorInfo(HttpStatus status, int code, String message, String developerMessage, String moreInfoUrl) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.moreInfoUrl = moreInfoUrl;
    }

    public ErrorInfo(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getMoreInfoUrl() {
        return moreInfoUrl;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
