package com.example.clearsolutions.exception;

import com.example.clearsolutions.constants.ServiceExceptionMessages;

public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException() {
        super(ServiceExceptionMessages.INVALID_DATE_RANGE);
    }
}