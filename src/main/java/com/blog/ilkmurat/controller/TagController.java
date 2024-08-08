package com.blog.ilkmurat.controller;

import com.blog.ilkmurat.model.Tag;
import com.blog.ilkmurat.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

}
