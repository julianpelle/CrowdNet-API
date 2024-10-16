package com.utn.CapitalConnection.exception;

public class EntrepreneurNonExistingException extends RuntimeException{
    public EntrepreneurNonExistingException(Long id) {
        super("Investor ID " + id + " does not exist.");
    }


}
