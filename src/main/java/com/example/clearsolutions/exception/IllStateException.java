package com.example.clearsolutions.exception;

import com.example.clearsolutions.constants.ServiceExceptionMessages;

public class IllStateException extends RuntimeException {
    public IllStateException() {
        super(ServiceExceptionMessages.USER_UNDERAGE);
    }
}
