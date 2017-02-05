package com.packman.exception;

/**
 * Created by mlshah on 12/6/15.
 */
public class PackmanException extends RuntimeException {

    private RestErrorMessages errorMessage;

    public PackmanException(){}

    public PackmanException(RestErrorMessages errorMessage, Throwable cause) {
        super(errorMessage.getMessage(), cause);
        this.errorMessage = errorMessage;
    }

    public PackmanException(RestErrorMessages errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public RestErrorMessages getErrorMessage() {
        return errorMessage;
    }
}
