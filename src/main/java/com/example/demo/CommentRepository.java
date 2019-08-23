
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;
@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Comment> findByPost(Post post);
}