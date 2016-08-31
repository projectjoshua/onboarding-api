package com.stg.exceptions;

public class InvalidParameterException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -7206683525558555848L;

    public InvalidParameterException() {
	super();
    }

    public InvalidParameterException(String message) {
	super(message);
    }
}
