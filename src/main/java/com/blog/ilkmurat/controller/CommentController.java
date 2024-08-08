package com.blog.ilkmurat.controller;


import com.blog.ilkmurat.model.Comment;
import com.blog.ilkmurat.model.Post;
import com.blog.ilkmurat.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    //----Retrieve comtentLisr by postId ---//
    @GetMapping(value = "/comments/{post_id}")
    public ResponseEntity<List<Comment>> getUserByUserId(@PathVariable("post_id") String post_id) {
        List<Comment> comments = commentRepository.getListById(post_id, "post_id", Comment.class);
        if (comments != null) {
            return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
        }
        return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
    }

    //----Create a post ----//
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<Void> createPost(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
