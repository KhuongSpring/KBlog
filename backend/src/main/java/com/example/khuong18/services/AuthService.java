package com.example.khuong18.services;

import com.example.khuong18.dtos.requests.LoginRequest;
import com.example.khuong18.dtos.responses.AuthResponse;

public interface AuthService {
    AuthResponse authentication(LoginRequest request);
}
