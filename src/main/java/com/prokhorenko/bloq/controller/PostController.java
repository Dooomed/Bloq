package com.prokhorenko.bloq.controller;

import com.prokhorenko.bloq.Dao.PostDao;
import com.prokhorenko.bloq.model.Post;
import com.prokhorenko.bloq.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PostController {

    @GetMapping({"/", "/homepage"})
    public String showHomepage(){
        return "homepage";
    }

    @GetMapping("/allPosts")
    public String showAllPosts(Model model){
        model.addAttribute("postList", PostService.findAllPosts());
        return "allPosts";
    }

    @PostMapping("/newPost")
    public String saveNewPost(@RequestParam String title, @RequestParam String body, Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Post post = new Post(title, body, dtf.format(now));
        PostService.savePost(post);
        return "redirect:/allPosts";
    }

    @GetMapping("/newPost")
    public String newPost(Model model){
        return "newPost";
    }

    @GetMapping("/delete/{id}")
    public String deletePosts(@PathVariable("id") int id, Model model){
        Post post = PostService.findPostById(id);
        PostService.deletePost(post);
        model.addAttribute("postList", PostService.findAllPosts());
        return "/allPosts";
    }

    @GetMapping("/allPosts/{id}")
    public String postDetails(Model model, @PathVariable(value = "id") int id){
        //PathVariable get date from http request
        Post post = PostService.findPostById(id);
        post.setViews(post.getViews() + 1);
        model.addAttribute("post", post);
        return "post";

    }
}
