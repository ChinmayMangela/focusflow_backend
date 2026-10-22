package com.example.chinmay.focusflow.controller;

import com.example.chinmay.focusflow.dto.CreateTaskRequest;
import com.example.chinmay.focusflow.dto.TaskResponse;
import com.example.chinmay.focusflow.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest createTaskRequest
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.createTask(createTaskRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> fetchTask(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(taskService.fetchTaskById(id));
    }
}
