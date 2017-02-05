package com.packman.exception;

/**
 * Created by RushikeshParadkar on 11/19/15.
 */
public class GenericDAOException extends RuntimeException {

    public GenericDAOException(final String message, final Throwable exception) {
        super(message, exception);
    }

    public GenericDAOException(final String message) {
        super(message);
    }
}