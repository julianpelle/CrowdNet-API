package com.utn.CapitalConnection.utils.log;

public enum LogCode {
    USER_CREATED("USER_CREATED", "User successfully created"),
    USER_UPDATED("USER_UPDATED", "User successfully updated"),
    USER_REMOVED("USER_REMOVED", "User successfully removed"),
    USER_DONT_FINDED("USER_NOT_FOUND", "User not found");

    private final String code;
    private final String description;

    LogCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
