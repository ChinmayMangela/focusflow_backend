package com.example.chinmay.focusflow.repository.specification;

import com.example.chinmay.focusflow.entity.Priority;
import com.example.chinmay.focusflow.entity.TaskEntity;

import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

    public static Specification<TaskEntity> hasPriority(Priority priority) {
        return (root, query, criteriaBuilder) ->

                priority == null ? null : criteriaBuilder.equal(root.get("priority"), priority);
    }

    public static Specification<TaskEntity> hasCompleted(Boolean completed) {
        return (root, query, criteriaBuilder) ->
                completed == null ? null : criteriaBuilder.equal(root.get("completed"), completed);
    }

}
