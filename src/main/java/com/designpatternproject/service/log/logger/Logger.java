package com.designpatternproject.service.log.logger;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Logger {
    private static Logger _loggerInstance;
    private LogLevel logLevel;
    private List<String> logs;
    private PrintWriter fileWriter;

    private Logger() {
        logLevel = LogLevel.INFO;
        logs = new ArrayList<>();
        try {
            fileWriter = new PrintWriter(new FileWriter("server.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Singleton instance retrieval
    public static Logger getInstance() {
        if (_loggerInstance == null) {
            synchronized (Logger.class) {
                if (_loggerInstance == null) {
                    _loggerInstance = new Logger();
                }
            }
        }
        return _loggerInstance;
    }

    // Set the log level
    public void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    // Log a message with the specified level
    public void log(LogLevel level, String message, String targetTable) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String log = String.format("[%s] [%s] %s", level, LocalDateTime.now(), message);
            fileWriter.println(log);
            fileWriter.flush();
        }
        logs.add(String.format("[%s] [%s] %s", level, LocalDateTime.now(), message));
    }

    // Display all logged messages
    public void displayLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}