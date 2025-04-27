package com.example.khuong18.dtos.requests;

import jakarta.validation.constraints.*;
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
    @Size(min = 8, message = "Username must be at least 8 characters")
    String username;

    @NotBlank(message = "Password not valid")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;

    @NotBlank(message = "fullName not valid")
    @Size(min = 10, message = "Full name must be at least 10 characters")
    String fullName;
}
