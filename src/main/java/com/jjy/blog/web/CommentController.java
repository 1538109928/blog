package com.jjy.blog.web;

import com.jjy.blog.dao.CommentRepository;
import com.jjy.blog.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments","");
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment) {

        return "redirect:/comments/" + comment.getBlog().getId();
    }
}
