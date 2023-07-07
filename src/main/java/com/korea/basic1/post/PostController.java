package com.korea.basic1.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String create(@Valid PostForm postForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "post_form";
        }

        this.postService.create(postForm.getSubject(), postForm.getContent(), postForm.getUploadFile());
        return "redirect:/post/list";
    }
    @GetMapping("/create")
    public String questionCreate(PostForm postForm) {
        return "post_form";
    }



}
