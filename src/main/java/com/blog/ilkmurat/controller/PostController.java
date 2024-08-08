package com.blog.ilkmurat.controller;

import com.blog.ilkmurat.model.Post;
import com.blog.ilkmurat.model.User;
import com.blog.ilkmurat.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    //----Create a post ----//
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity<Void> createPost(@RequestBody Post post) {
        postRepository.save(post);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value ="/posts/{postId}")
    public ResponseEntity<Post> getPostByPostId(@PathVariable("postId") String postId) {
        return postRepository.findById(postId)
                .map(post -> ResponseEntity.ok().body(post))
                .orElse(ResponseEntity.notFound().build());
    }
}
