package com.example.khuong18.controllers.post;

import com.example.khuong18.constrants.BaseUrl;
import com.example.khuong18.constrants.SuccessMessage;
import com.example.khuong18.dtos.requests.post.PostCreationRequest;
import com.example.khuong18.dtos.responses.ApiResponse;
import com.example.khuong18.dtos.responses.post.PostResponse;
import com.example.khuong18.services.PostService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @GetMapping(BaseUrl.Post.GET_POSTS)
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPosts() {
        return ResponseEntity.ok(ApiResponse.<List<PostResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(SuccessMessage.Post.SUCCESS_GET_DATA)
                .result(postService.getPosts())
                .build());
    }
}
