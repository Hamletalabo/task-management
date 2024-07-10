package org.hamlet.taskmgt.service;

import org.hamlet.taskmgt.payload.request.LoginRequest;
import org.hamlet.taskmgt.payload.request.UserRequest;
import org.hamlet.taskmgt.payload.response.ApiResponse;
import org.hamlet.taskmgt.payload.response.JwtAuthResponse;
import org.hamlet.taskmgt.payload.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    UserResponse registerUser(UserRequest request);

    ResponseEntity<ApiResponse<JwtAuthResponse>> login(LoginRequest loginRequest);
}
