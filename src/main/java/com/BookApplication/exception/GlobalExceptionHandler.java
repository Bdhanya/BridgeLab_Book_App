package com.BookApplication.exception;

import com.BookApplication.configuration.ApplicationConfig;
import com.BookApplication.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Response> bookNotFound(BookNotFoundException bookNotFoundException)
    {

        return new ResponseEntity<Response>(
                new Response(HttpStatus.NOT_FOUND.value(), ApplicationConfig.getMessageAccessor().getMessage("102"), ""), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<Response> authorNotFound(AuthorNotFoundException notFound)
    {

        return new ResponseEntity<Response>(
                new Response(HttpStatus.NOT_FOUND.value(), ApplicationConfig.getMessageAccessor().getMessage("108"), ""), HttpStatus.NOT_FOUND);
    }
}
