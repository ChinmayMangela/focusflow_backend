package com.example.chinmay.focusflow.mapper;

import com.example.chinmay.focusflow.dto.TaskResponse;
import com.example.chinmay.focusflow.entity.TaskEntity;

public class TaskMapper {

    public static TaskResponse toResponse(TaskEntity task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }
}
