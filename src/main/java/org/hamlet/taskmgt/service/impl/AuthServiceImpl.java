package org.hamlet.taskmgt.service.impl;

import lombok.RequiredArgsConstructor;
import org.hamlet.taskmgt.model.entity.UserEntity;
import org.hamlet.taskmgt.model.enums.Role;
import org.hamlet.taskmgt.payload.request.EmailDetails;
import org.hamlet.taskmgt.payload.request.LoginRequest;
import org.hamlet.taskmgt.payload.request.UserRequest;
import org.hamlet.taskmgt.payload.response.ApiResponse;
import org.hamlet.taskmgt.payload.response.JwtAuthResponse;
import org.hamlet.taskmgt.payload.response.UserResponse;
import org.hamlet.taskmgt.payload.response.UserResponseInfo;
import org.hamlet.taskmgt.repository.UserRepository;
import org.hamlet.taskmgt.service.AuthService;
import org.hamlet.taskmgt.service.EmailService;
import org.hamlet.taskmgt.utils.AccountUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    @Override
    public UserResponse registerUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            UserResponse response = UserResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .build();

            return response;
        }

        UserEntity newUser = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dateOfBirth(request.getDateOfBirth())
                .profilePic("https://res.cloudinary.com/dpfqbb9pl/image/upload/v1701260428/maleprofile_ffeep9.png")
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        UserEntity saveUser = userRepository.save(newUser);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(saveUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("CONGRATULATION!!! Your account has been successfully created. "
                        + "\n Your Account Details: \n " + "Account Name : "
                +saveUser.getFirstname()
                +saveUser.getLastname())
                .build();
        emailService.sendEmailAlert(emailDetails);

        return UserResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .responseInfo(UserResponseInfo.builder()
                        .firstname(saveUser.getFirstname())
                        .lastname(saveUser.getLastname())
                        .build())
                .build()
                ;
    }

    @Override
    public ResponseEntity<ApiResponse<JwtAuthResponse>> login(LoginRequest loginRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userEntityOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            "Login Failed: Invalid email or password",
                            null));
        }

        EmailDetails loginAlert = EmailDetails.builder()
                .subject("You are logged in")
                .recipient(loginRequest.getEmail())
                .messageBody("You logged into your account. If you did not initiate this request, contact support desk.")
                .build();

        emailService.sendEmailAlert(loginAlert);

        UserEntity user = userEntityOptional.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        "Login Successfully",
                        JwtAuthResponse.builder()
                                .id(user.getId())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .phoneNumber(user.getPhoneNumber())
                                .dateOfBirth(user.getDateOfBirth())
                                .accessToken("access token here")
                                .tokenType("Bearer")
                                .build())
                );
    }
}
