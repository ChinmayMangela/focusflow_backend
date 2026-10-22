package com.example.chinmay.focusflow.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ApiErrorResponse {
    private int status;
    private String message;
    private Map<String, String> errors;
}
