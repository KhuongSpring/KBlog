package com.example.khuong18.controllers;


import com.example.khuong18.constrants.SuccessMessage;
import com.example.khuong18.dtos.requests.user.UserCreationRequest;
import com.example.khuong18.dtos.requests.LoginRequest;
import com.example.khuong18.dtos.responses.ApiResponse;
import com.example.khuong18.dtos.responses.AuthResponse;
import com.example.khuong18.services.AuthService;
import com.example.khuong18.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthController {

    AuthService authService;
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.authentication(request);
        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .result(authResponse)
                .message(SuccessMessage.Auth.SUCCESS_LOG_IN)
                .code(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody UserCreationRequest request) {
        if (userService.createUser(request)) {
            return ResponseEntity.ok(ApiResponse.<String>builder()
                    .result(SuccessMessage.Auth.SUCCESS_SIGN_UP)
                    .code(HttpStatus.CREATED.value())
                    .build());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
