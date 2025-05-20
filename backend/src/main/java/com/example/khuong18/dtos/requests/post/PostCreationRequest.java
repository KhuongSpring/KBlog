package com.example.khuong18.dtos.requests.post;

import com.example.khuong18.constrants.ErrorMessage;
import com.example.khuong18.entites.enums.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCreationRequest {

    @NotBlank(message = ErrorMessage.Post.ERR_CONTENT_EMPTY)
    String content;

    @NotNull(message = ErrorMessage.Post.ERR_TYPE_NOT_VALID)
    PostType type;

    @NotNull(message = ErrorMessage.Post.ERR_CREATE_TIME_NOT_VALID)
    LocalDateTime createdAt;

    @NotNull(message = ErrorMessage.Post.ERR_OWNER_ID_NOT_VALID)
    Long owner_id;
}
