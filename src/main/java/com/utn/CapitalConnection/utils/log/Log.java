package com.utn.CapitalConnection.utils.log;

public interface Log {
    void registerAction(LogCode Logcode);

    <T> void registerAction(LogCode Logcode, T object);
}

