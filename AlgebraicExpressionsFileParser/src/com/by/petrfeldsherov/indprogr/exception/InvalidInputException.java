package com.by.petrfeldsherov.indprogr.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {
    private String invalidValue;

    public InvalidInputException(String invalidValue) {
	this.invalidValue = invalidValue;
    }

    public InvalidInputException(String message, String invalidValue) {
	super(message);
	this.invalidValue = invalidValue;
    }

    public String getInvalidValue() {
	return invalidValue;
    }
}