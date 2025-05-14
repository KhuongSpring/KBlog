package com.example.khuong18.dtos.requests.user;

import com.example.khuong18.constrants.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreationUserRequest {
    @Email(message = ErrorMessage.User.ERR_EMAIL_NOT_VALID)
    String email;

    @NotBlank(message = ErrorMessage.User.ERR_USERNAME_NOT_VALID)
    @Size(min = 8, message = "Username must be at least 8 characters")
    String username;

    @NotBlank(message = ErrorMessage.User.ERR_PASSWORD_NOT_VALID)
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;

    @NotBlank(message = ErrorMessage.User.ERR_FULL_NAME_NOT_VALID)
    @Size(min = 10, message = "Full name must be at least 10 characters")
    String fullName;
}
