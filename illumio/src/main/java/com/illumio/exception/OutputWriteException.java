package com.illumio.exception;

public class OutputWriteException extends FlowLogParserException {
    public OutputWriteException(String message) {
        super(message);
    }

    public OutputWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
