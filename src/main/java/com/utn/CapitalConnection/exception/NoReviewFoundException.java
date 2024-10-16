package com.utn.CapitalConnection.exception;

public class NoReviewFoundException extends RuntimeException{
    public NoReviewFoundException() {
        super("No entrepreneurships found");
    }
    public NoReviewFoundException(String message) {
        super(message);
    }
    public NoReviewFoundException(Long id) {
        super("No review found with id: " + id);
    }
}
