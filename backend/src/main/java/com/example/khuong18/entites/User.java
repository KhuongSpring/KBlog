package com.example.khuong18.entites;

import com.example.khuong18.entites.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    int age;

    @Column(unique = true, nullable = false)
    String email;

    String gender;
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(nullable = false)
    String fullName;
    String bio;
    String connectLink;
    String avatar;

}
