package com.packman.Util;

import org.springframework.http.HttpStatus;

/**
 * Created by mlshah on 5/4/16.
 */
public class SuccessResponse {

    private HttpStatus status;
    private String message;

    public SuccessResponse() {
    }

    public SuccessResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}