package com.example.khuong18.repositories;

import com.example.khuong18.entites.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
