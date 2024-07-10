package org.hamlet.taskmgt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String profilePic;

    private String email;

    private String accessToken;

    private String tokenType = "Bearer";
}
