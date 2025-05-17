package com.example.khuong18.services.impls;

import com.example.khuong18.constrants.ErrorMessage;
import com.example.khuong18.dtos.requests.user.CreationUserRequest;
import com.example.khuong18.dtos.requests.user.UserUpdateProfileRequest;
import com.example.khuong18.dtos.responses.user.UserResponse;
import com.example.khuong18.entites.user.User;
import com.example.khuong18.entites.enums.Role;
import com.example.khuong18.exceptions.CustomException;
import com.example.khuong18.repositories.UserRepository;
import com.example.khuong18.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User u : users) {
            userResponses.add(modelMapper.map(u, UserResponse.class));
        }
        return userResponses;
    }

    @Override
    public boolean createUser(CreationUserRequest request) {
        if (userRepository.existsUserByUsername(request.getUsername()))
            throw new CustomException(ErrorMessage.User.ERR_USERNAME_EXISTED, HttpStatus.CONFLICT);
        if (userRepository.existsUserByEmail(request.getEmail()))
            throw new CustomException(ErrorMessage.User.ERR_EMAIL_EXISTED, HttpStatus.CONFLICT);

        User user = modelMapper.map(request, User.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        if (!userRepository.existsUserByUsername(username))
            throw new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST);

        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateProfileUserById(UserUpdateProfileRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST));

        user.setBio(request.getBio());
        user.setConnectLink(request.getConnectLink());
        user.setGender(request.getGender());

        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void updateAvatar(String url, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST));

        user.setAvatar(url);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getUsersByKeyword(String keyword) {
        List<User> users = userRepository.findByUsernameContainingIgnoreCase(keyword);

        List<UserResponse> responses = new ArrayList<>();
        for(User u : users){
            responses.add(modelMapper.map(u, UserResponse.class));
        }
        return responses;
    }

    @Transactional
    @Override
    public void updateFollow(Long myId, Long targetId) {
        User myUser = userRepository.findById(myId)
                .orElseThrow(() -> new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST));
        User targetUser = userRepository.findById(targetId)
                .orElseThrow(() -> new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST));

        if (!myUser.getFollowings().contains(targetUser)) {
            myUser.getFollowings().add(targetUser);
            myUser.setFollowing(myUser.getFollowing() + 1);
            targetUser.setFollower(targetUser.getFollower() + 1);
        }
    }

}
