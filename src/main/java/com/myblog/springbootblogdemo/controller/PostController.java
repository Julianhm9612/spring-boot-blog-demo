package com.myblog.springbootblogdemo.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.myblog.springbootblogdemo.entity.Post;
import com.myblog.springbootblogdemo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(path = "")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "/{id}")
    public Optional<Post> getPost(@NotNull @PathVariable BigInteger id) {
        return postService.getPost(id);
    }

    @PostMapping(path = "")
    public Post addPost(@Valid @RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping(path = "")
    public Post updatePost(@Valid @RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@NotNull @PathVariable BigInteger id) {
        postService.deletePost(id);
    }

    @GetMapping(path = "/native")
    public List<Post> getAllNative() {
        return postService.getAllNative();
    }

    @GetMapping(path = "/user/{id}")
    public List<Post> getAllNative(@NotNull @PathVariable BigInteger id) {
        return postService.getByUserId(id);
    }

    @GetMapping(path = "/user/function/{id}")
    public ResponseEntity<Object> getByUserIdInFunction(@NotNull @PathVariable BigInteger id) {
        HashMap<String, String> map = new HashMap<>();
        try {
            return new ResponseEntity<Object>(postService.getByUserIdInFunction(id), HttpStatus.OK);
        } catch (Exception e) {
            map.put("status", "false");
            map.put("message", "Opp, ha ocurrido el siguiente error:" + e.getMessage());
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }
    }

}
