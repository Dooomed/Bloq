package com.prokhorenko.bloq.controller;

import com.prokhorenko.bloq.model.Post;
import com.prokhorenko.bloq.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public String saveNewPost(@RequestParam String title, @RequestParam String body){
        // RequestParam get date from view
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Post post = new Post(title, body, dtf.format(now));
        PostService.savePost(post);
        return "redirect:/allPosts";
    }

    @GetMapping("/newPost")
    public String newPost(){
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
        // PathVariable get date from http request
        Post post = PostService.findPostById(id);
        post.incrementViews();
        PostService.updatePost(post);
        model.addAttribute("post", post);
        return "post";

    }

    @GetMapping("/allPosts/{id}/edit")
    public String postEdit(Model model, @PathVariable(value = "id") int id){
        Post post = PostService.findPostById(id);
        model.addAttribute("post", post);
        return "post-edit";
    }

    @PostMapping("/allPosts/{id}/edit")
    public String blogPostUpdate(@PathVariable (value = "id") int id, @RequestParam String title, @RequestParam String body){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Post post = PostService.findPostById(id);
        post.setTitle(title);
        post.setBody(body);
        post.setDate(dtf.format(now).concat(" edited"));
        PostService.updatePost(post);
        return "redirect:/allPosts";
    }

}
