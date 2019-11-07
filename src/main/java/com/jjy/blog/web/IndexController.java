package com.jjy.blog.web;

import com.jjy.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

       /* String blog = null;
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }*/
       System.out.println("----------index------------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {

       /* String blog = null;
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }*/
        System.out.println("----------index------------");
        return "blog";
    }

}
