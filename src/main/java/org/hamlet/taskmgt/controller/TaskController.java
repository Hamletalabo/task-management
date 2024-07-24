package org.hamlet.taskmgt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hamlet.taskmgt.model.entity.Task;
import org.hamlet.taskmgt.model.enums.Category;
import org.hamlet.taskmgt.model.enums.PriorityLevel;
import org.hamlet.taskmgt.model.enums.Status;
import org.hamlet.taskmgt.payload.request.TaskRequest;
import org.hamlet.taskmgt.payload.response.TaskResponse;
import org.hamlet.taskmgt.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create_task")
    public ResponseEntity<TaskResponse> createTask(String email, @RequestBody @Valid TaskRequest request) {
        TaskResponse response = taskService.createTask(email, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view_completed_tasks")
    public ResponseEntity<List<TaskResponse>> viewCompletedTasks(@RequestParam String email) {
        List<TaskResponse> responses = taskService.viewCompletedTasks(email);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/delete_task/{taskId}")
    public ResponseEntity<TaskResponse> deleteTask(@RequestParam String email, @PathVariable Long taskId) {
        TaskResponse response = taskService.deleteTaskById(email, taskId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks_by_category")
    public ResponseEntity<List<Task>> findTasksByCategory(@RequestParam String email, @RequestParam Category category) {
        List<Task> tasks = taskService.findByCategory(email, category);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks_by_category_and_status")
    public ResponseEntity<List<Task>> findTasksByCategoryAndStatus(@RequestParam String email, @RequestParam Category category, @RequestParam Status status) {
        List<Task> tasks = taskService.findByCategoryAndStatus(email, category, status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks_by_status")
    public ResponseEntity<List<Task>> findTasksByStatus(@RequestParam String email, @RequestParam Status status) {
        List<Task> tasks = taskService.findByStatus(email, status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks_by_priority")
    public ResponseEntity<List<Task>> findTasksByPriorityLevel(@RequestParam String email, @RequestParam PriorityLevel priorityLevel) {
        List<Task> tasks = taskService.findByPriorityLevel(email, priorityLevel);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update_task/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@RequestParam String email, @PathVariable Long taskId, @RequestBody @Valid TaskRequest request) {
        TaskResponse response = taskService.updateTask(email, taskId, request);
        return ResponseEntity.ok(response);
    }
}
