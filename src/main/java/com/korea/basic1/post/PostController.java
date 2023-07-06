package com.korea.basic1.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/create")
    public String create()
    {
        return "post_form";
    }
}
