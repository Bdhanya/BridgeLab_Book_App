package com.BookApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookException extends RuntimeException{
    private int statusCode;

    private String statusMessage;
}
