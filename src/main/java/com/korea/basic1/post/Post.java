package com.korea.basic1.post;

import java.time.LocalDateTime;
import java.util.List;

import com.korea.basic1.comment.Comment;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;


    // 이미지(사진)
    @NotEmpty
    private String file_name;
    @NotEmpty
    private String file_path;


    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Where(clause = "parent_id is null")
    private List<Comment> commentList;
}