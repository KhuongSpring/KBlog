package com.example.khuong18.entites.user;

import com.example.khuong18.entites.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(exclude = {"followers", "followings"})
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

    @ManyToMany
    @JoinTable(
            name = "user_followings",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    Set<User> followings = new HashSet<>();

    @ManyToMany(mappedBy = "followings")
    Set<User> followers = new HashSet<>();

    int follower;
    int following;
    int post;
}
