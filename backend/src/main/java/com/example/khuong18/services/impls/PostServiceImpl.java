package com.example.khuong18.services.impls;

import com.example.khuong18.constrants.ErrorMessage;
import com.example.khuong18.dtos.requests.post.PostCreationRequest;
import com.example.khuong18.dtos.responses.post.CommentResponse;
import com.example.khuong18.dtos.responses.post.PostResponse;
import com.example.khuong18.dtos.responses.user.UserResponse;
import com.example.khuong18.entites.post.Post;
import com.example.khuong18.entites.user.User;
import com.example.khuong18.exceptions.CustomException;
import com.example.khuong18.repositories.PostRepository;
import com.example.khuong18.services.PostService;
import com.example.khuong18.services.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {
    ModelMapper modelMapper;
    PostRepository postRepository;
    UserService userService;

    @Override
    public PostResponse createPost(PostCreationRequest request) {
        if (userService.getUserById(request.getOwner_id()) == null)
            throw new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        Post post = modelMapper.map(request, Post.class);
        post.setOwner(modelMapper.map(
                userService.getUserById(request.getOwner_id()), User.class));
        postRepository.save(post);
        return null;
    }

    @Override
    public List<PostResponse> getPosts() {
        List<PostResponse> responses = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post p : posts) {
            responses.add(toPostResponse(p));
        }
        return responses;
    }

    public PostResponse toPostResponse(Post post) {
        PostResponse dto = new PostResponse();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setType(post.getType().name());
        dto.setCreatedAt(post.getCreatedAt());

        User owner = post.getOwner();
        dto.setOwner(modelMapper.map(owner, UserResponse.class));

        dto.setLikedUserIds(post.getLikedUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList()));

        dto.setSavedUserIds(post.getSavedUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList()));

//        dto.setComments(post.getComments().stream().map(comment -> {
//            User commenter = comment.getCommenter();
//            return new CommentResponse(
//                    comment.getId(),
//                    comment.getContent(),
//                    comment.getCreatedAt(),
//                    new UserInfoDTO(commenter.getId(), commenter.getUsername())
//            );
//        }).collect(Collectors.toList()));

        return dto;
    }

}
