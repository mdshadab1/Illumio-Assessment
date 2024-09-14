package com.illumio.exception;

public class LookupTableLoadException extends FlowLogParserException {
//    public LookupTableLoadException(String message) {
//        super(message);
//    }

    public LookupTableLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
