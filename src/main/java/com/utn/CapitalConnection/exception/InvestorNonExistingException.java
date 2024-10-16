package com.utn.CapitalConnection.exception;

public class InvestorNonExistingException extends RuntimeException{
    public InvestorNonExistingException(Long id) {
        super("Investor ID " + id + " does not exist.");
    }
    public InvestorNonExistingException(String message) {
        super(message);
    }
}
