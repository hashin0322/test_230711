package com.korea.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/basic")
    @ResponseBody
    public String index() {
        return "안녕하세요";
    }


    @GetMapping("/")
    public String root() {
        return "redirect:/post/list";
    }

}
