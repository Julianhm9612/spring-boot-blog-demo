package com.myblog.springbootblogdemo.service;

import java.util.List;

import com.myblog.springbootblogdemo.dto.ClientDto;

public interface ClientService {
    void save(final ClientDto client);

    void update(final ClientDto client);

    // TODO cp: to be protected!
    List<ClientDto> getAll();
}
