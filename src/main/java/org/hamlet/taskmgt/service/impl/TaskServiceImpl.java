package org.hamlet.taskmgt.service.impl;

import lombok.RequiredArgsConstructor;
import org.hamlet.taskmgt.model.entity.Task;
import org.hamlet.taskmgt.payload.request.TaskRequest;
import org.hamlet.taskmgt.payload.response.ApiResponse;
import org.hamlet.taskmgt.payload.response.TaskResponse;
import org.hamlet.taskmgt.repository.TaskRepository;
import org.hamlet.taskmgt.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTask() {

        return taskRepository.findAll();
    }

    @Override
    public List<TaskResponse> viewCompletedTask(String email) {

        return List.of();
    }

    @Override
    public void deleteTaskById(String email, Long taskId) {

    }

    @Override
    public List<Task> findByCategory(String email, String category) {
        return List.of();
    }

    @Override
    public List<Task> findByCategoryAndStatus(String email, String category, String status) {
        return List.of();
    }

    @Override
    public List<Task> findByStatus(String email, String status) {
        return List.of();
    }

    @Override
    public List<Task> findByPriorityLevel(String email, String priorityLeve) {
        return List.of();
    }

    @Override
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(String email, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(String email, TaskRequest taskRequest) {
        return null;
    }
}
