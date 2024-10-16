package com.utn.CapitalConnection.exception;

public class InvalidMediaFormatException extends Exception {
    public InvalidMediaFormatException(String mediaUrl) {
        super("Invalid media format for URL: " + mediaUrl + ". Ensure it is a valid image or video link.");
    }
}
