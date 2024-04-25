package com.example.clearsolutions.exception;

import com.example.clearsolutions.constants.ServiceExceptionMessages;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(ServiceExceptionMessages.USER_NOT_FOUND);
    }
}
