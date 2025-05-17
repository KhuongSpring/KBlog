package com.example.khuong18.services;

import com.example.khuong18.dtos.requests.user.CreationUserRequest;
import com.example.khuong18.dtos.requests.user.UserUpdateProfileRequest;
import com.example.khuong18.dtos.responses.user.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();

    boolean createUser(CreationUserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByUsername(String username);

    UserResponse updateProfileUserById(UserUpdateProfileRequest request);

    void updateAvatar(String url, Long id);

    List<UserResponse> getUsersByKeyword(String keyword);

    void updateFollow(Long myId, Long targetId);

    boolean checkFollow(Long myId, Long targetId);
}
