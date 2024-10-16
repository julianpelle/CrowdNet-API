package com.utn.CapitalConnection.exception;

import java.time.LocalDateTime;

public class InvalidDateException extends Exception {
    public InvalidDateException(LocalDateTime date) {
        super("Invalid donation date: " + date + ". The date cannot be in the future.");
    }
}
