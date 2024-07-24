package org.hamlet.taskmgt.service;

import org.hamlet.taskmgt.model.entity.Task;
import org.hamlet.taskmgt.model.enums.Category;
import org.hamlet.taskmgt.model.enums.PriorityLevel;
import org.hamlet.taskmgt.model.enums.Status;
import org.hamlet.taskmgt.payload.request.TaskRequest;
import org.hamlet.taskmgt.payload.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks(String email);
    List<TaskResponse> viewCompletedTasks(String email);

    TaskResponse deleteTaskById(String email, Long taskId);

    List<Task> findByCategory(String email, Category category);

    List<Task> findByCategoryAndStatus(String email, Category category, Status status);

    List<Task> findByStatus(String email, Status status);

    List<Task> findByPriorityLevel(String email, PriorityLevel priorityLevel);

    TaskResponse createTask(String email, TaskRequest taskRequest);

    TaskResponse updateTask(String email, Long taskId, TaskRequest taskRequest);

    TaskResponse toTaskResponse(Task task);
}
