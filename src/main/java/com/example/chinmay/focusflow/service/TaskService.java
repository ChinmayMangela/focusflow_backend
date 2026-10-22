package com.example.chinmay.focusflow.service;


import com.example.chinmay.focusflow.dto.CreateTaskRequest;
import com.example.chinmay.focusflow.dto.TaskResponse;
import com.example.chinmay.focusflow.entity.TaskEntity;
import com.example.chinmay.focusflow.exception.TaskNotFoundException;
import com.example.chinmay.focusflow.mapper.TaskMapper;
import com.example.chinmay.focusflow.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {
        TaskEntity task = new TaskEntity();
        task.setTitle(createTaskRequest.title());
        task.setDescription(createTaskRequest.description());
        task.setPriority(createTaskRequest.priority());

        TaskEntity savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }

    public TaskResponse fetchTaskById(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return TaskMapper.toResponse(task);
    }

    public List<TaskResponse> fetchAllTasks() {
        return taskRepository.findAll().stream().map(TaskMapper::toResponse).toList();
    }
}
