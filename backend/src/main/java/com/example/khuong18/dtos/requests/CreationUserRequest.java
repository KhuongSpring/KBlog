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

    @NotBlank(message = "Gender not valid")
    String gender;

    @NotNull(message = "Age not valid")
    @Min(value = 10, message = "Age must be at least 10")
    @Max(value = 80, message = "Age must not be smaller 80")
    int age;
}
