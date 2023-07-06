package com.korea.basic1.comment;

import com.korea.basic1.DataNotFoundException;
import com.korea.basic1.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment getComment(Integer id){
        Optional<Comment> comment = this.commentRepository.findById(id);
        if( comment.isPresent()){  // 해당 데이터가 존재하는지 검사
            return comment.get();
        }
        else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public void create(Post post, String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setPost(post);
        this.commentRepository.save(comment);
    }

    public void createReply(Post post, Comment parentComment, String replyContent){
        Comment reply = new Comment();
        reply.setContent(replyContent);
        reply.setCreateDate(LocalDateTime.now());
        reply.setPost(post);
        reply.setParent(parentComment);
        this.commentRepository.save(reply);
    }

}
