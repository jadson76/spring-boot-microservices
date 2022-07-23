package com.jadson.microservices.buyticket.exceptions;

public class JsonMapException extends RuntimeException{
    public JsonMapException(String message) {
        super(message);
    }

    public JsonMapException(String message, Throwable cause) {
        super(message, cause);
    }
}
