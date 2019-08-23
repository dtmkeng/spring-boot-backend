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