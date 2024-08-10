package com.spring_cloud.eureka.client.order.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HttpClientRequestException.class)
    public final ResponseEntity<String> ClientRequestException(HttpClientRequestException ex, WebRequest request) {
        return new ResponseEntity<String> (ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public final ResponseEntity<String> noSuchElementException(NoSuchElementException ex, WebRequest request) {
        return new ResponseEntity<String> (ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
