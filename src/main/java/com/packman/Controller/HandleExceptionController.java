package com.packman.controller;

import com.packman.exception.ErrorInfo;
import com.packman.exception.PackmanException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Created by mlshah on 12/6/15.
 */
@ControllerAdvice
public class HandleExceptionController  extends ResponseEntityExceptionHandler{

    private final String documentationLink = "www.google.com";

    // there are methods which check for input parameters in controller using @valid annotation
    // we can do that as well, if needed
    // for http request, should it be same or we can even customize it for each exception in the errorInfo or packmanException class
    // it will be minimal code change based on what we decide on http code
    // Also, errorInfo object can be incorporated in packmanException as well but that will require forming errorinfo object
    // on every exception
    //TODO: decide on which messages and should there be exception in dao like genericDao which ultimately gets catched in servie


    @ExceptionHandler({ PackmanException.class })
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        PackmanException ire = (PackmanException) e;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorInfo er = new ErrorInfo(HttpStatus.BAD_REQUEST, ire.getErrorMessage().getCode(), ire.getErrorMessage().getMessage(), "developer is here",documentationLink);
       return new ResponseEntity(er, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        logger.error(e);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorInfo er = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error ", "developer is here",documentationLink);
        return new ResponseEntity(er, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex);
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorInfo er = new ErrorInfo(HttpStatus.BAD_REQUEST, 422, "Invalid Json Format", "developer is here",documentationLink);
        return new ResponseEntity(er, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
