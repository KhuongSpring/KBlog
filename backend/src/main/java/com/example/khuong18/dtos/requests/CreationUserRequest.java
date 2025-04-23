package com.example.khuong18.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreationUserRequest {
    String email;
    String username;
    String password;
    String gender;
    int age;
    String role;
}
