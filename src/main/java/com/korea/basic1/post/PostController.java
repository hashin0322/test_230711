package com.korea.basic1.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/post/list")
    public String list(Model model)
    {
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);
        return "post_list";
    }

    @GetMapping(value = "/post/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id)
    {
        Post post = this.postService.getPost(id);

        model.addAttribute("post", post);
        return "post_detail";
    }

    @PostMapping("/post/create")
    public String create(@RequestParam String subject, @RequestParam List<MultipartFile> files, @RequestParam String content) {

        this.postService.create(subject, files, content);
        return "redirect:/post/list";
    }


    @GetMapping("/post/create")
    public String questionCreate(PostForm postForm) {

        return "post_form";
    }



    @PostMapping("/board")
    public ResponseEntity<?> createPost( @Validated @RequestParam("files") List<MultipartFile> files ) throws Exception {
        postService.addPost(Post.builder().build(), files);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/board")
    public String getBoard(@RequestParam int id) {
        Post post = postService.findPost(id).orElseThrow(RuntimeException::new);
        String imgPath = post.getStoredFileName();
        log.info(imgPath);
        return "<img src=" + imgPath + ">";
    }


}
