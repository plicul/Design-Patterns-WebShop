package com.designpatternproject.service.log;

public interface LogService {
    public void add(String level, String targetTable, String text);
}
