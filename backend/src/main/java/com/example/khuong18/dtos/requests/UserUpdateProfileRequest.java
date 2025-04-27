package com.example.khuong18.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateProfileRequest {
    String userName;

    @NotBlank(message = "Bio not valid")
    @Size(min = 3, message = "Bio must be at least 3 characters")
    String bio;
    String connectLink;
    String gender;
}
