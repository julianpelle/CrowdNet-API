package com.utn.CapitalConnection.exception;

import java.util.Date;

public class InvalidDateOfBirthException extends Exception {
    public InvalidDateOfBirthException(Date dateOfBirth) {
        super("Invalid date of birth: " + dateOfBirth + ". The date of birth cannot be in the future.");
    }
}
