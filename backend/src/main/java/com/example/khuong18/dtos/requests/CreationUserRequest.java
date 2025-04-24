package com.example.khuong18.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreationUserRequest {
    @Email
    String email;
    @NotBlank(message = "Username not valid")
    String username;
    @NotBlank(message = "Password not valid")
    String password;
    @NotBlank(message = "Gender not valid")
    String gender;
    @NotNull(message = "Age not valid")
    int age;
}
