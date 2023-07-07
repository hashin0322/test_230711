package com.korea.basic1.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    private String subject;
    private String content;

    @NotEmpty(message = "이미지는 필수 항목입니다.")
    private String uploadFile;
}
