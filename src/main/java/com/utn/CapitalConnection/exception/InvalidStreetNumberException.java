package com.utn.CapitalConnection.exception;

public class InvalidStreetNumberException extends Exception {
    public InvalidStreetNumberException(String number) {
        super("Invalid street number: " + number + ". It must be a valid number.");
    }
}