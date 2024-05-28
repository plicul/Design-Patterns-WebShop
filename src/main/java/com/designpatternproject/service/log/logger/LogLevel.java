package com.designpatternproject.service.log.logger;

public enum LogLevel {
    INFO(0),
    WARN(1),
    ERROR(2),
    IMPORTANT_INFO(3);

    final int level;

    LogLevel(int level) {
        this.level = level;
    }
}
