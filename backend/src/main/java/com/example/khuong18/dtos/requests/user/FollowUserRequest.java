package com.example.khuong18.dtos.requests.user;

import com.example.khuong18.constrants.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowUserRequest {

    @NotBlank(message = ErrorMessage.User.ERR_USERNAME_NOT_VALID)
    String myUsername;
    @NotBlank(message = ErrorMessage.User.ERR_USERNAME_NOT_VALID)
    String targetUsername;
}
