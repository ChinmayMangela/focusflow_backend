package com.example.chinmay.focusflow.dto;

import com.example.chinmay.focusflow.entity.Priority;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateTaskPatchRequest(
        @Pattern(
                regexp = "^(?=.*\\S).+$",
                message = "Title cannot be blank"
        )
        @Size(min = 2, max = 100, message = INVALID_TITLE_LENGTH_MESSAGE)
        String title,

        @Size(max = 1000, message = INVALID_DESCRIPTION_LENGTH_MESSAGE)
        String description,

        Priority priority,

        Boolean completed
) {

    private static final String INVALID_TITLE_LENGTH_MESSAGE = "Title must be of length between 2 to 100 characters";
    private static final String INVALID_DESCRIPTION_LENGTH_MESSAGE = "Description must be of length between 0 to 1000 characters";

}

