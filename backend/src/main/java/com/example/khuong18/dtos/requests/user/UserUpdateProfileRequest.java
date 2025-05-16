package com.example.khuong18.dtos.requests.user;

import com.example.khuong18.constrants.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateProfileRequest {
    @NotNull(message = ErrorMessage.User.ERR_ID_NOT_VALID)
    Long id;

    @NotBlank(message = ErrorMessage.User.ERR_BIO_NOT_VALID)
    @Size(min = 3, message = "Bio must be at least 3 characters")
    String bio;

    String connectLink;
    String gender;
}
