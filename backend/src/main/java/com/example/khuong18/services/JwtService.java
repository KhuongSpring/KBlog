package com.example.khuong18.services;
public interface JwtService {
    // Tạo token
    String generateToken(String username);
}
