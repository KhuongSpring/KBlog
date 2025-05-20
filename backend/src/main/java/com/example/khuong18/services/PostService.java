package com.example.khuong18.services;

import com.example.khuong18.dtos.requests.post.PostCreationRequest;
import com.example.khuong18.dtos.responses.post.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostCreationRequest request);

    List<PostResponse> getPosts();
}
