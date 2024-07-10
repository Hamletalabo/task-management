package org.hamlet.taskmgt.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hamlet.taskmgt.model.enums.Category;
import org.hamlet.taskmgt.model.enums.PriorityLevel;
import org.hamlet.taskmgt.model.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "task_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Task extends BaseClass {

    private String title;
    private String description;
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private PriorityLevel priorityLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference // this prevents the task list from looping over
    private UserEntity user;
}
