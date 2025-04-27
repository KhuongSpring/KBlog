package com.example.khuong18.controllers;

import com.example.khuong18.dtos.requests.UserUpdateProfileRequest;
import com.example.khuong18.dtos.responses.UserResponse;
import com.example.khuong18.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "userId") Long id){
//        return ResponseEntity.ok(userService.getUserById(id));
//    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable(name = "userName") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping()
    public ResponseEntity<UserResponse> updateUserByUserName(@Valid @RequestBody UserUpdateProfileRequest request){
        return ResponseEntity.ok(userService.updateProfileUserByUserName(request));
    }
}
