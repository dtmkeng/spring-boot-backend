package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);
    Post findByTitle(String title);
}