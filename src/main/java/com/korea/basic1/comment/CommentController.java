package com.korea.basic1.comment;

import com.korea.basic1.post.Post;
import com.korea.basic1.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @RequestParam("content") String content)
    {
        Post post = this.postService.getPost(id);

        this.commentService.create(post, content);

        return String.format("redirect:/post/detail/%s", id);
    }

    @PostMapping("/create/{postId}/{commentId}")
    public String createReply(Model model, @PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId, @RequestParam("replyContent") String replyContent)
    {
        Post post = this.postService.getPost(postId);

        Comment parentComment = commentService.getComment(commentId);

        commentService.createReply(post, parentComment, replyContent);

        return String.format("redirect:/post/detail/%s", postId);
    }
}

