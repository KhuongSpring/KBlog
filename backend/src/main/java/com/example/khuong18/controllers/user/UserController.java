package com.example.khuong18.controllers.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.khuong18.dtos.requests.user.UserUpdateProfileRequest;
import com.example.khuong18.dtos.responses.user.UserResponse;
import com.example.khuong18.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    Cloudinary cloudinary;

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

    @PostMapping("/upload_avatar")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam(name = "userName") String userName) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) result.get("secure_url");
        userService.updateAvatar(imageUrl, userName);
        return ResponseEntity.ok(imageUrl);
    }

}
