package com.utn.CapitalConnection.exception;

public class UserNonExistingException extends RuntimeException{
    public UserNonExistingException(Long id) {
        super("User ID" + id + "does not exist.");
    }

}
