package com.myblog.springbootblogdemo.dao;

import java.math.BigInteger;
import java.util.List;

import com.myblog.springbootblogdemo.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, BigInteger> {

    public List<Post> findAllByOrderByUserIdAsc();

    @Query(value = "SELECT * FROM post ORDER BY user_id", nativeQuery = true)
    public List<Post> getAllNative();

    @Query(value = "SELECT p FROM Post p WHERE user.id = ?1")
    public List<Post> getByUserId(BigInteger id);

    // @Query(value = "SELECT p FROM Post p WHERE user.id = :id")
    // public List<Post> getByUserId(@Param("id") BigInteger id);

    // public List<Post> getByUserId(@Param("id") BigInteger id);

}
