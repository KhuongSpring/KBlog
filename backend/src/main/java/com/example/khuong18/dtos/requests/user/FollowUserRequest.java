package com.example.khuong18.dtos.requests.user;

import com.example.khuong18.constrants.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowUserRequest {

    @NotNull(message = ErrorMessage.User.ERR_ID_NOT_VALID)
    Long myId;
    @NotNull(message = ErrorMessage.User.ERR_ID_NOT_VALID)
    Long targetId;
}
