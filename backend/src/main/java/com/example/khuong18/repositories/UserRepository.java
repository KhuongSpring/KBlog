package com.example.khuong18.repositories;

import com.example.khuong18.entites.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);

    User findByUsername(String username);

    Optional<User> findUserDetailByUsername(String username);


    boolean existsUserByEmail(@Email String email);

    List<User> findByUsernameContainingIgnoreCase(String keyword);

}
