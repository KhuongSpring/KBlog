package com.example.khuong18.services;

import com.example.khuong18.dtos.requests.CreationUserRequest;
import com.example.khuong18.dtos.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    boolean createUser(CreationUserRequest request);
}
