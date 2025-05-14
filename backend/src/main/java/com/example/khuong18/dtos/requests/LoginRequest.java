package com.example.khuong18.dtos.requests;

import com.example.khuong18.constrants.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = ErrorMessage.Auth.ERR_LOGIN_FAIL)
    String username;
    @NotBlank(message = ErrorMessage.Auth.ERR_LOGIN_FAIL)
    String password;
}
