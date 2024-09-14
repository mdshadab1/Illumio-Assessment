package com.illumio.exception;

public class InvalidFlowLogEntryException extends FlowLogParserException {

    public InvalidFlowLogEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
