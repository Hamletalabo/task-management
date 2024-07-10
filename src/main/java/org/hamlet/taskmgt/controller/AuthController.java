package org.hamlet.taskmgt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hamlet.taskmgt.payload.request.LoginRequest;
import org.hamlet.taskmgt.payload.request.UserRequest;
import org.hamlet.taskmgt.payload.response.ApiResponse;
import org.hamlet.taskmgt.payload.response.JwtAuthResponse;
import org.hamlet.taskmgt.payload.response.UserResponse;
import org.hamlet.taskmgt.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register-user")
    public UserResponse registerUser(@Valid @RequestBody UserRequest request){
        return authService.registerUser(request);
    }

    @PostMapping("/login-user")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
