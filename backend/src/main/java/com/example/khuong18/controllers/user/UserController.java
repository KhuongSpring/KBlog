package com.example.khuong18.controllers.user;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.khuong18.constrants.BaseUrl;
import com.example.khuong18.constrants.SuccessMessage;
import com.example.khuong18.dtos.requests.user.FollowUserRequest;
import com.example.khuong18.dtos.requests.user.UserUpdateProfileRequest;
import com.example.khuong18.dtos.responses.ApiResponse;
import com.example.khuong18.dtos.responses.user.UserResponse;
import com.example.khuong18.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    Cloudinary cloudinary;

    @GetMapping(BaseUrl.User.GET_USERS)
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_GET_DATA)
                .result(userService.getUsers())
                .build());
    }

    @GetMapping(BaseUrl.User.GET_USER_BY_ID)
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_GET_DATA)
                .result(userService.getUserById(id))
                .build());
    }

    @GetMapping(BaseUrl.User.GET_USER_BY_USERNAME)
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUserName(@PathVariable(name = "userName") String username) {
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_GET_DATA)
                .result(userService.getUserByUsername(username))
                .build());
    }

    @PutMapping(BaseUrl.User.UPDATE_USER)
    public ResponseEntity<ApiResponse<UserResponse>> updateUserById(@Valid @RequestBody UserUpdateProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_UPDATE_DATA)
                .result(userService.updateProfileUserById(request))
                .build());
    }

    @PostMapping(BaseUrl.User.UPLOAD_AVATAR)
    public ResponseEntity<ApiResponse<String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "id") Long id
    ) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) result.get("secure_url");
        userService.updateAvatar(imageUrl, id);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_UPDATE_DATA)
                .result(imageUrl)
                .build());
    }

    @GetMapping(BaseUrl.User.SEARCH_USERS_BY_KEYWORD)
    public ResponseEntity<ApiResponse<List<UserResponse>>> getListUserByKeyWord(@PathVariable(name = "keyword") String word) {
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_GET_DATA)
                .result(userService.getUsersByKeyword(word))
                .build());
    }

    @PostMapping(BaseUrl.User.FOLLOW_USER)
    public ResponseEntity<ApiResponse<String>> followUser(@Valid @RequestBody FollowUserRequest request) {
        Long myId = request.getMyId();
        Long targetId = request.getTargetId();

        userService.updateFollow(myId, targetId);

        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_UPDATE_DATA)
                .result(myId + " followed " + targetId)
                .build());
    }

    @PostMapping(BaseUrl.User.UNFOLLOW_USER)
    public ResponseEntity<ApiResponse<String>> unfollowUser(@Valid @RequestBody FollowUserRequest request) {
        Long myId = request.getMyId();
        Long targetId = request.getTargetId();

        userService.updateFollow(myId, targetId);

        return ResponseEntity.ok(ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_UPDATE_DATA)
                .result(myId + " unfollowed " + targetId)
                .build());
    }

    @GetMapping(BaseUrl.User.CHECK_FOLLOW_USER)
    public ResponseEntity<ApiResponse<Boolean>> checkFollowUser(
            @RequestParam(name = "myId") Long myId,
            @RequestParam(name = "targetId") Long targetId
    ) {
        boolean isFollowed = userService.checkFollow(myId, targetId);
        return ResponseEntity.ok(ApiResponse.<Boolean>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_GET_DATA)
                .result(isFollowed)
                .build());
    }

    @GetMapping(BaseUrl.User.SHOW_FOLLOW_USER)
    public ResponseEntity<ApiResponse<List<UserResponse>>> showFollow(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "isFollower") boolean isFollower
    ) {
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.User.SUCCESS_GET_DATA)
                .result(userService.showFollow(id, isFollower))
                .build());
    }

}
