package com.myblog.springbootblogdemo.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.myblog.springbootblogdemo.dao.PostDao;
import com.myblog.springbootblogdemo.dao.OutputFindPostsDao;
import com.myblog.springbootblogdemo.entity.OutputFindPosts;
import com.myblog.springbootblogdemo.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostDao postDao;

    @Autowired
    OutputFindPostsDao outputFindPostsDao;

    public List<Post> getPosts() {
        return this.postDao.findAllByOrderByUserIdAsc();
    }

    public Optional<Post> getPost(BigInteger id) {
        return this.postDao.findById(id);
    }

    public Post addPost(Post post) {
        return this.postDao.save(post);
    }

    public Post updatePost(Post post) {
        return this.postDao.save(post);
    }

    public void deletePost(BigInteger id) {
        Optional<Post> post = this.getPost(id);
        if (post.isPresent()) {
            this.postDao.deleteById(id);
        }
    }

    public List<Post> getAllNative() {
        return this.postDao.getAllNative();
    }

    public List<Post> getByUserId(BigInteger id) {
        return this.postDao.getByUserId(id);
    }

    public List<OutputFindPosts> getByUserIdInFunction(BigInteger id) {
        return this.outputFindPostsDao.getByUserIdInFunction(id);
    }

}
