
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