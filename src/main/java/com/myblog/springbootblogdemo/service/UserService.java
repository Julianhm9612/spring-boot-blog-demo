package com.myblog.springbootblogdemo.service;

import java.util.List;

import com.myblog.springbootblogdemo.dto.UserDto;

import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(final UserDto user);

    int update(UserDto user);

    List<UserDto> getUsersForClient(String clientId, final Pageable pageable);

}
