package com.example.khuong18.security;


import com.example.khuong18.constrants.ErrorMessage;
import com.example.khuong18.entites.user.User;
import com.example.khuong18.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findUserDetailByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.User.ERR_USER_NOT_FOUND + username));
        return new CustomUserDetails(user);
    }


}
