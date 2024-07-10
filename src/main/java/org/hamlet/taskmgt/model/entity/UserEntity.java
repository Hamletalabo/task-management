package org.hamlet.taskmgt.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hamlet.taskmgt.model.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tbl")
@Entity
@Builder
public class UserEntity extends BaseClass{

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String profilePic;

    private String email;

    private String password;

    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> task = new ArrayList<>();

}
