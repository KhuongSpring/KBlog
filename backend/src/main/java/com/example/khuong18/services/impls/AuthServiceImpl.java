package com.example.khuong18.services.impls;

import com.example.khuong18.constrants.ErrorMessage;
import com.example.khuong18.dtos.requests.LoginRequest;
import com.example.khuong18.dtos.responses.AuthResponse;
import com.example.khuong18.entites.user.User;
import com.example.khuong18.exceptions.CustomException;
import com.example.khuong18.repositories.UserRepository;
import com.example.khuong18.services.AuthService;
import com.example.khuong18.services.JwtService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    JwtService jwtService;

    @Override
    public AuthResponse authentication(LoginRequest request) {
        if (!userRepository.existsUserByUsername(request.getUsername()))
            throw new CustomException(ErrorMessage.User.ERR_USER_NOT_FOUND, HttpStatus.BAD_REQUEST);

        User user = userRepository.findByUsername(request.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean auth = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!auth) throw new CustomException(ErrorMessage.Auth.ERR_LOGIN_FAIL, HttpStatus.BAD_REQUEST);

        var token = jwtService.generateToken(request.getUsername());
        return AuthResponse.builder()
                .authentication(true)
                .token(token)
                .build();
    }
}
