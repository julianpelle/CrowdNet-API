package com.utn.CapitalConnection.exception;

public class NoEntrepreneurshipsFoundException extends RuntimeException {

    public NoEntrepreneurshipsFoundException() {
        super("No entrepreneurships found");
    }
    public NoEntrepreneurshipsFoundException(String message) {
        super(message);
    }
    public NoEntrepreneurshipsFoundException(Long id) {
        super("No entrepreneurships found with id " + id);
    }
}