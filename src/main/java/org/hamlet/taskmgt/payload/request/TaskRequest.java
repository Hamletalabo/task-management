package org.hamlet.taskmgt.payload.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hamlet.taskmgt.model.enums.Category;
import org.hamlet.taskmgt.model.enums.PriorityLevel;
import org.hamlet.taskmgt.model.enums.Status;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    private String title;

    private String description;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private PriorityLevel priorityLevel;

}
