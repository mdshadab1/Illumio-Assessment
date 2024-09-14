package com.illumio.exception;

public class FlowLogParserException extends RuntimeException {
    public FlowLogParserException(String message) {
        super(message);
    }

    public FlowLogParserException(String message, Throwable cause) {
        super(message, cause);
    }
}

