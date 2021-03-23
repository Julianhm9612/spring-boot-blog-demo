package com.myblog.springbootblogdemo.dao;

import java.math.BigInteger;
import java.util.List;

import com.myblog.springbootblogdemo.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, BigInteger> {

    public List<Post> findAllByOrderByUserIdAsc();

}
