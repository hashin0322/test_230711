package com.korea.basic1.post;

import java.time.LocalDateTime;
import java.util.List;

import com.korea.basic1.comment.Comment;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer postIdx;

    @Column(length = 200)
    private String subject;


    // 이미지(사진)
    // 원본 파일이름과 서버에 저장된 파일 경로를 분리
    // 동일한 이름을 가진 파일이 업로드가 된다면 오류가 발생 -> 이를 해결하기 위함
//    @NotNull
    private String originalFileName;
//    @NotNull
    private String storedFileName;
    private long fileSize;


    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Where(clause = "parent_id is null")
    private List<Comment> commentList;


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}