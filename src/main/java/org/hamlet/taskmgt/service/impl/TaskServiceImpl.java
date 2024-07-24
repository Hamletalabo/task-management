package org.hamlet.taskmgt.service.impl;

import lombok.RequiredArgsConstructor;
import org.hamlet.taskmgt.model.entity.Task;
import org.hamlet.taskmgt.model.entity.UserEntity;
import org.hamlet.taskmgt.model.enums.Category;
import org.hamlet.taskmgt.model.enums.PriorityLevel;
import org.hamlet.taskmgt.model.enums.Status;
import org.hamlet.taskmgt.payload.request.TaskRequest;
import org.hamlet.taskmgt.payload.response.TaskInfo;
import org.hamlet.taskmgt.payload.response.TaskResponse;
import org.hamlet.taskmgt.repository.TaskRepository;
import org.hamlet.taskmgt.repository.UserRepository;
import org.hamlet.taskmgt.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Task> getAllTasks(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return taskRepository.findAll().stream()
                .filter(task -> task.getUser().getEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> viewCompletedTasks(String email) {
        Status status = Status.COMPLETED;
        List<Task> tasks = taskRepository.findByStatus(status);

        // Filter tasks by email and map to TaskResponseDto
        List<TaskResponse> taskResponseDtos = tasks.stream()
                .filter(task -> task.getUser().getEmail().equals(email))
                .map(task -> TaskResponse.builder()
                        .responseCode("005")
                        .responseMessage("Get Task by Status was a Success")
                        .taskInfo(TaskInfo.builder()
                                .title(task.getTitle())
                                .description(task.getDescription())
                                .deadline(task.getDeadline())
                                .category(task.getCategory())
                                .priorityLevel(task.getPriorityLevel())
                                .status(task.getStatus())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return taskResponseDtos;
    }

    @Override
    public TaskResponse deleteTaskById(String email, Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            return TaskResponse.builder()
                    .responseCode("404")
                    .responseMessage("Task not found")
                    .build();
        }

        Task task = optionalTask.get();
        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You do not have permission to delete this task");
        }

        taskRepository.deleteById(taskId);
        return TaskResponse.builder()
                .responseCode("008")
                .responseMessage("Task deleted successfully")
                .build();
    }

    @Override
    public List<Task> findByCategory(String email, Category category) {
        validateUser(email);
        return taskRepository.findByCategory(category).stream()
                .filter(task -> task.getUser().getEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByCategoryAndStatus(String email, Category category, Status status) {
        validateUser(email);
        return taskRepository.findByCategoryAndStatus(category, status).stream()
                .filter(task -> task.getUser().getEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByStatus(String email, Status status) {
        validateUser(email);
        return taskRepository.findByStatus(status).stream()
                .filter(task -> task.getUser().getEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByPriorityLevel(String email, PriorityLevel priorityLevel) {
        validateUser(email);
        return taskRepository.findByPriorityLevel(priorityLevel).stream()
                .filter(task -> task.getUser().getEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse createTask(String email, TaskRequest taskRequest) {
        UserEntity user = validateUser(email);
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .deadline(taskRequest.getDeadline())
                .priorityLevel(taskRequest.getPriorityLevel())
                .category(taskRequest.getCategory())
                .status(Status.PENDING)
                .user(user)
                .build();
        taskRepository.save(task);

        return toTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(String email, Long taskId, TaskRequest taskRequest) {
        Task task = validateTaskOwnership(email, taskId);

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDeadline(taskRequest.getDeadline());
        task.setCategory(taskRequest.getCategory());
        task.setPriorityLevel(taskRequest.getPriorityLevel());
        task.setStatus(taskRequest.getStatus());

        Task updatedTask = taskRepository.save(task);
        return toTaskResponse(updatedTask);
    }

    private UserEntity validateUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    private Task validateTaskOwnership(String email, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));

        if (!task.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You do not have permission to edit this task");
        }
        return task;
    }

    public TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .responseCode("200")
                .responseMessage("Success")
                .taskInfo(TaskInfo.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .deadline(task.getDeadline())
                        .category(task.getCategory())
                        .priorityLevel(task.getPriorityLevel())
                        .status(task.getStatus())
                        .build())
                .build();
    }
}
