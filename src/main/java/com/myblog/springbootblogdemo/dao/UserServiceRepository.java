package com.myblog.springbootblogdemo.dao;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.myblog.springbootblogdemo.entity.UserEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepository extends AuthorizationRepository<UserEntity, BigInteger> {

    boolean existsByUsername(final String username);

    boolean existsByEmailAddress(final String emailAddress);

    Optional<UserEntity> getByUsername(final String username);

    @Modifying
    @Query("update UserEntity u set " + "u.emailAddress=?1, u.name=?2, u.surname=?3, u.username=?4" + " where u.id=?5")
    @Transactional
    int update(final String emailAddress, final String firstName, final String lastName, final String username,
            final BigInteger id);

    Stream<UserEntity> getAllByClientId(final String clientId, final Pageable pageable);

    @Transactional
    int deleteByUsername(final String username);

    Optional<UserEntity> getById(final BigInteger id);
}