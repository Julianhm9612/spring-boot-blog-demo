package com.myblog.springbootblogdemo.dao;

import java.math.BigInteger;
import java.util.List;

import com.myblog.springbootblogdemo.entity.OutputFindPosts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputFindPostsDao extends JpaRepository<OutputFindPosts, BigInteger> {

    // @Procedure(name = "Post.getByUserIdInFunction")
    @Query(value = "select id, title, userid user_id FROM public.find_posts_by_user(:id)", nativeQuery = true)
    public List<OutputFindPosts> getByUserIdInFunction(@Param("id") BigInteger id);

}
