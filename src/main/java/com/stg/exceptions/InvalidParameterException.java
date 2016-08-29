package com.stg.exceptions;

public class InvalidParameterException extends Exception {

    public InvalidParameterException() {
	super();
    }

    public InvalidParameterException(String message) {
	super(message);
    }
}
