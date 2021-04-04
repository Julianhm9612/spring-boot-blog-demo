package com.myblog.springbootblogdemo.dao;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AuthorizationRepository<T, ID extends Serializable> extends Repository<T, ID> {

    @Modifying
    @Transactional
    <S extends T> S save(S entity);

    Stream<T> findAll();
}