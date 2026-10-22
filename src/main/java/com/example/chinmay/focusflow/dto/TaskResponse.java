package com.example.chinmay.focusflow.dto;

import com.example.chinmay.focusflow.entity.Priority;

import java.time.OffsetDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        Priority priority,
        boolean completed,
        OffsetDateTime createdAt
) {
}
