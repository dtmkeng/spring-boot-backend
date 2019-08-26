
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import org.springframework.data.domain.Page;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PostController {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CommentRepository commentRepository;

    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @GetMapping("/post")
    public Collection<Post> Post() {
        return this.postRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/post/{postid}")
    public Post PostId(@PathVariable("postid") Long postid ) {
        return this.postRepository.findByPostId(postid);
    }
}
