package com.korea.basic1.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/list")
    public String list(Model model)
    {
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);
        return "post_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id)
    {
        Post post = this.postService.getPost(id);

        model.addAttribute("post", post);
        return "post_detail";
    }

    @PostMapping("/create")
    public String create(@RequestParam String subject, @RequestParam List<MultipartFile> files, @RequestParam String content) {

        this.postService.create(subject, files, content);
        return "redirect:/post/list";
    }


    @GetMapping("/create")
    public String questionCreate(PostForm postForm) {

        return "post_form";
    }



    @PostMapping("/post")
    public ResponseEntity<?> createPost(
            @Validated @RequestParam("files") List<MultipartFile> files
    ) throws Exception {
        postService.addPost(Post.builder().build(), files);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/post")
    public String getBoard(@RequestParam long id) {
        Post post = postService.findpost(id).orElseThrow(RuntimeException::new);
        String imgPath = post.getStoredFileName();
        log.info(imgPath);
        return "<img src=" + imgPath + ">";
    }


}
