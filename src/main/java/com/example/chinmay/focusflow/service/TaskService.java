package com.example.chinmay.focusflow.service;


import com.example.chinmay.focusflow.dto.CreateTaskRequest;
import com.example.chinmay.focusflow.dto.TaskResponse;
import com.example.chinmay.focusflow.dto.UpdateTaskPatchRequest;
import com.example.chinmay.focusflow.dto.UpdateTaskPutRequest;
import com.example.chinmay.focusflow.entity.Priority;
import com.example.chinmay.focusflow.entity.TaskEntity;
import com.example.chinmay.focusflow.exception.TaskNotFoundException;
import com.example.chinmay.focusflow.mapper.TaskMapper;
import com.example.chinmay.focusflow.repository.TaskRepository;
import com.example.chinmay.focusflow.repository.specification.TaskSpecifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    public List<TaskResponse> fetchAllTasks(
            Priority priority,
            Boolean completed
    ) {
        final Specification<TaskEntity> specification = Specification.where(
                TaskSpecifications.hasPriority(priority)
                        .and(TaskSpecifications.hasCompleted(completed))
        );
        return taskRepository.findAll(
                specification
        ).stream().map(TaskMapper::toResponse).toList();
    }

    public TaskResponse updateTaskPut(
            Long oldTaskId,
            UpdateTaskPutRequest newTask
    ) {
        TaskEntity existingTask = taskRepository.findById(oldTaskId)
                .orElseThrow(() -> new TaskNotFoundException(oldTaskId));

        existingTask.setTitle(newTask.title());
        existingTask.setDescription(newTask.description());
        existingTask.setPriority(newTask.priority());
        existingTask.setCompleted(newTask.completed());

        TaskEntity updatedTask = taskRepository.save(existingTask);
        return TaskMapper.toResponse(updatedTask);

    }

    public TaskResponse updateTaskPatch(
            Long oldTaskId,
            UpdateTaskPatchRequest newTask
    ) {
        TaskEntity existingTask = taskRepository.findById(oldTaskId)
                .orElseThrow(() -> new TaskNotFoundException(oldTaskId));

        if (newTask.title() != null) {
            existingTask.setTitle(newTask.title());
        }
        if (newTask.description() != null) {
            existingTask.setDescription(newTask.description());
        }
        if (newTask.priority() != null) {
            existingTask.setPriority(newTask.priority());
        }
        if (newTask.completed() != null) {
            existingTask.setCompleted(newTask.completed());
        }

        TaskEntity updatedTask = taskRepository.save(existingTask);
        return TaskMapper.toResponse(updatedTask);
    }


    public void deleteTask(Long id) {
        TaskEntity taskToBeDelete = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(taskToBeDelete);
    }
}
