## การโยงความสัมพันธ์แบบ M--1 and 1--M
ในกรณีที่ต้องการเพิ่มข้อมูลจากฝัง one เช่น post บน fb โยงกับ comment
### ตัวอย่าง 
1. การเพิ่มข้อมูล
``` java
Post ps = new Post(); // สร้าง object 
ps.setTitle("test"); // เพิ่มข้อมูล 
Post ps1 = postRepository.findByPostId(postRepository.save(ps).getPostId()); // บันทึกข้อมูลเเล้ว getid ของที่บันทึก
```
2. เพิ่มข้อมูลในส่วน comment
``` java
        // วนรูปเพิ่ม comment
Stream.of("Takoonkan", "Sitthichai", "Somchai", "Tanapon").forEach(name -> {
	Comment cm = new Comment();
	cm.setTitle(name);
	cm.setPost(ps1);  // set id ทีโยงกับ post
	commentRepository.save(cm);
});
```

### Entity
#### Post entity
```java

package com.example.demo;


import lombok.*;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.util.*;
import javax.persistence.*;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Data
public class Post {
    @Id
    @SequenceGenerator(name = "Post_seq", sequenceName = "Post_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post_seq")
    @NotNull private  Long postId;

    @NotNull
    private String title;

 
    @OneToMany(fetch = FetchType.EAGER ,mappedBy="post")
    @JsonManagedReference
    private Collection<Comment> comment;
}
```
#### Comment entity
```java
package com.example.demo;


import lombok.*;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;
@Entity
@Data
public class Comment {
    @Id
    @SequenceGenerator(name = "Comment_seq", sequenceName = "Comment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Comment_seq")
    @NotNull private  Long commentId;

    @NotNull
    private String title;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="post_id")
    @JsonIgnore
    private Post post;
}
```
### Repostitory
```java
// post Repostitory
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);
    Post findByTitle(String title);
}
///////////////////////////////////////////////////////////////////////////

// comment Repository
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;
@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Comment> findByPost(Post post);
}
```
### Controller
```java

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
    public Post Post() {
        return postRepository.findByTitle("test");
    }
}
```