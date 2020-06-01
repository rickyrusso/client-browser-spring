package com.arkpes.clientbrowser.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidClientDataException extends RuntimeException {
    public InvalidClientDataException(String message){
        super(message);
    }
}
