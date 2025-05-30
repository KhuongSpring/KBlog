package com.example.khuong18.dtos.responses.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String email;
    String username;
    String gender;
    int age;
    String role;
    String fullName;
    String bio;
    String connectLink;
    String avatar;
    int follower;
    int following;
    int post;
}
