package com.example.chinmay.focusflow.dto;

import com.example.chinmay.focusflow.entity.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank(message = INVALID_TITLE_MESSAGE)
        @Size(min = 2, max = 100, message = INVALID_TITLE_LENGTH_MESSAGE)
        String title,

        @Size(max = 1000, message = INVALID_DESCRIPTION_LENGTH_MESSAGE)
        String description,

        @NotNull(message = NULL_PRIORITY_MESSAGE)
        Priority priority
) {

    private static final String INVALID_TITLE_MESSAGE = "Valid title is mandatory";
    private static final String INVALID_TITLE_LENGTH_MESSAGE = "Title must be of length between 2 to 100 characters";
    private static final String INVALID_DESCRIPTION_LENGTH_MESSAGE = "Description must be of length between 0 to 1000 characters";
    private static final String NULL_PRIORITY_MESSAGE = "Priority is mandatory";

}
