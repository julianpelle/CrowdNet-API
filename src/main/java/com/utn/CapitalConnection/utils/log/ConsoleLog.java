package com.utn.CapitalConnection.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("consoleLog")
public class ConsoleLog implements Log {
    private final Logger logger = LoggerFactory.getLogger(ConsoleLog.class);

    @Override
    public void registerAction(LogCode logcode) {
        logger.info("Action: " + logcode.getCode() + " - " + logcode.getDescription());
    }

    @Override
    public <T> void registerAction(LogCode logcode, T object) {
        logger.info("Action: " + logcode.getCode() + " - " + object.toString());
    }
}
