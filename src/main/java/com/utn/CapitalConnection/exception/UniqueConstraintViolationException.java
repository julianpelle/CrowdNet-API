package com.utn.CapitalConnection.exception;

    public class UniqueConstraintViolationException extends RuntimeException {
        public UniqueConstraintViolationException(String message) {
            super(message);
        }
    }

