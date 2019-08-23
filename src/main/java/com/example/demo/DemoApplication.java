package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	ApplicationRunner init(PostRepository postRepository,CommentRepository commentRepository) {
		return args -> {
			// Create o
			Post ps = new Post();
			ps.setTitle("test");
			postRepository.save(ps);
			Post ps1 = postRepository.findByTitle("test");
			Stream.of("Takoonkan", "Sitthichai", "Somchai", "Tanapon").forEach(name -> {
				Comment cm = new Comment();
				cm.setTitle(name);
				cm.setPost(ps1);
				commentRepository.save(cm);
				ps1.getComment().add(cm);
				// postRepository.getComment().add(cm);
			});
			// postRepository.save(ps1);
			// postRepository.findAll().forEach(System.out::println); 
			
		};
	}


}
