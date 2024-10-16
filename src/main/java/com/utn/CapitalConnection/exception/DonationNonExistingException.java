package com.utn.CapitalConnection.exception;


public class DonationNonExistingException extends RuntimeException {
    public DonationNonExistingException(String message) {
        super(message);
    }


    public DonationNonExistingException(Long id) {
        super("Donation ID " + id + " does not exist.");
    }
}