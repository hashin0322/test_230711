package com.korea.basic1.comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.korea.basic1.post.Post;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 댓글(부모)
    @ManyToOne
    private Comment parent;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 댓글(부모)-답글(자식) 설정
    @OneToMany(mappedBy = "parent"  , orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    private LocalDateTime createDate;

    @ManyToOne
    private Post post;


    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}