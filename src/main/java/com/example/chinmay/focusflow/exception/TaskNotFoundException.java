package com.example.chinmay.focusflow.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException {
    private final Long id;

    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " not found");
        this.id = id;
    }
}
