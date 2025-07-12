package com.petr.spring.task.logger_task.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileLogger implements CustomLogger {
    private final Logger logger;

    public FileLogger(@Value("${logging.file.path}") String logFilePath) {
        System.setProperty("logFilename", logFilePath);
        this.logger = LogManager.getLogger(FileLogger.class);
    }


    @Override
    public void log(String message) {
        logger.info(message);
    }
}

