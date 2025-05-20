package com.example.khuong18.dtos.responses.post;

import com.example.khuong18.dtos.responses.user.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    Long id;
    String content;
    String type;
    LocalDateTime createdAt;

    UserResponse owner;

    List<Long> likedUserIds;
    List<Long> savedUserIds;

    List<CommentResponse> comments;

    List<String> hashtags;
}
