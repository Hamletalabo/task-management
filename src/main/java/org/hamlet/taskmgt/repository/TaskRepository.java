package org.hamlet.taskmgt.repository;

import org.hamlet.taskmgt.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
   List<Task> findByCategory(String category);

   List<Task> findByCategoryAndStatus(String category, String status);

   List<Task> findByStatus(String status);

   List<Task>findByPriorityLevel(String priorityLeve);

}
