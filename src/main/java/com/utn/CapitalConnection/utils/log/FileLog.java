package com.utn.CapitalConnection.utils.log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("fileLog")
public class FileLog implements Log {
    private final Logger logger = LoggerFactory.getLogger(ConsoleLog.class);

    @Override
    public void registerAction(LogCode logcode) {
        String logMessage = "FileLog: Acción: " + logcode.getCode() + " - " + logcode.getDescription();
        writeToFile(logMessage);
    }

    @Override
    public <T> void registerAction(LogCode logcode, T object) {
        String logMessage = "FileLog: Acción: " + logcode.getCode() + " - " + logcode.getDescription() + " - " + object.toString();
        writeToFile(logMessage);
    }

    private void writeToFile(String message) {
        logger.error(message);
    }
}
