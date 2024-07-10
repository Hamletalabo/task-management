package org.hamlet.taskmgt.payload.response;

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
public class UserResponseInfo {

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String profilePic;

    private String email;


}
