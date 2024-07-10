package org.hamlet.taskmgt.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hamlet.taskmgt.model.enums.Role;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {


    @NotBlank(message = "firstname cannot be empty")
    private String firstname;

    @NotBlank(message = "lastname cannot be empty")
    private String lastname;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String profilePic;

    private String email;

    private String password;

}
