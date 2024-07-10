package org.hamlet.taskmgt.service;

import org.hamlet.taskmgt.model.entity.Task;
import org.hamlet.taskmgt.payload.request.TaskRequest;
import org.hamlet.taskmgt.payload.response.ApiResponse;
import org.hamlet.taskmgt.payload.response.TaskResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {



    List<Task> getAllTask();
    List<TaskResponse> viewCompletedTask(String email);

    void deleteTaskById(String email,Long taskId);

    List<Task> findByCategory(String email, String category);

    List<Task> findByCategoryAndStatus(String email, String category, String status);

    List<Task> findByStatus(String email, String status);

    List<Task>findByPriorityLevel(String email, String priorityLeve);

    ResponseEntity<ApiResponse<TaskResponse>> createTask(String email, TaskRequest taskRequest);

    ResponseEntity<ApiResponse<TaskResponse>> updateTask(String email, TaskRequest taskRequest);

}
