package com.example.chinmay.focusflow.controller;

import com.example.chinmay.focusflow.dto.CreateTaskRequest;
import com.example.chinmay.focusflow.dto.TaskResponse;
import com.example.chinmay.focusflow.dto.UpdateTaskPatchRequest;
import com.example.chinmay.focusflow.dto.UpdateTaskPutRequest;
import com.example.chinmay.focusflow.entity.Priority;
import com.example.chinmay.focusflow.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TaskResponse>> fetchAllTasks(
            @Valid @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Boolean completed
            ) {
        return ResponseEntity.ok(taskService.fetchAllTasks(
                priority,
                completed
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTaskPut(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskPutRequest updateTaskPutRequest
    ) {
        TaskResponse updatedTask = taskService.updateTaskPut(id, updateTaskPutRequest);
        return ResponseEntity.ok(updatedTask);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTaskPatch(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskPatchRequest updateTaskPatchRequest
    ) {
        TaskResponse updatedTask = taskService.updateTaskPatch(id, updateTaskPatchRequest);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id
    ) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
