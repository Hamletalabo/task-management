package org.hamlet.taskmgt.repository;

import org.hamlet.taskmgt.model.entity.Task;
import org.hamlet.taskmgt.model.enums.Category;
import org.hamlet.taskmgt.model.enums.PriorityLevel;
import org.hamlet.taskmgt.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

   List<Task> findByCategory(Category category);

   List<Task> findByCategoryAndStatus(Category category, Status status);

   List<Task> findByStatus(Status status);

   List<Task> findByPriorityLevel(PriorityLevel priorityLevel);
   Long countAllByStatus(Status status);
   Long countByStatus(Status status);
}
