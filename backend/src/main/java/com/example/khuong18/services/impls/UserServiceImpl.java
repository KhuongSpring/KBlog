package com.example.khuong18.services.impls;

import com.example.khuong18.dtos.requests.CreationUserRequest;
import com.example.khuong18.dtos.requests.UserUpdateProfileRequest;
import com.example.khuong18.dtos.responses.UserResponse;
import com.example.khuong18.entites.User;
import com.example.khuong18.entites.enums.Role;
import com.example.khuong18.exceptions.CustomException;
import com.example.khuong18.repositories.UserRepository;
import com.example.khuong18.services.UserService;
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
            throw new CustomException("Username existed", HttpStatus.CONFLICT);
        if (userRepository.existsUserByEmail(request.getEmail()))
            throw new CustomException("Email existed", HttpStatus.CONFLICT);
        User user = modelMapper.map(request, User.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("Not found user", HttpStatus.BAD_REQUEST));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateProfileUserByUserName(UserUpdateProfileRequest request) {
        User user = userRepository.findByUsername(request.getUserName());
        if (user == null) throw new CustomException("Not found user", HttpStatus.FORBIDDEN);
        user.setBio(request.getBio());
        user.setConnectLink(request.getConnectLink());
        user.setGender(request.getGender());
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }
}
