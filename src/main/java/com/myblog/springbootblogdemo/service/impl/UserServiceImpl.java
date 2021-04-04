package com.myblog.springbootblogdemo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.myblog.springbootblogdemo.dao.UserServiceRepository;
import com.myblog.springbootblogdemo.dto.UserDto;
import com.myblog.springbootblogdemo.entity.UserEntity;
import com.myblog.springbootblogdemo.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service
public class UserServiceImpl implements UserService {
    private final UserServiceRepository userServiceRepository;
    private final ModelMapper modelMapper;
    private final ClientDetailsService clientDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserServiceRepository userServiceRepository, final ModelMapper modelMapper,
            final ClientDetailsService clientDetailsService, final PasswordEncoder passwordEncoder) {
        this.userServiceRepository = userServiceRepository;
        this.modelMapper = modelMapper;
        this.clientDetailsService = clientDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(final UserDto user) {
        final ClientDetails clientDetails = clientDetailsService.loadClientByClientId(user.getClientId());
        if (clientDetails == null) {
            throw new BadCredentialsException("Unauthorized to save user");
        }
        final UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setUserRoles(getRoles(clientDetails));
        // userEntity.setId(UUID.randomUUID());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return modelMapper.map(userServiceRepository.save(userEntity), UserDto.class);
    }

    @Override
    public int update(final UserDto user) {
        assertNotNull(user.getId(), "Id is null and it shouldn't");
        final String password = userServiceRepository.getById(user.getId()).orElseThrow(EntityNotFoundException::new)
                .getPassword();
        if (passwordEncoder.matches(user.getOldPassword(), password)) {
            return userServiceRepository.update(user.getEmailAddress(), user.getFirstName(), user.getLastName(),
                    user.getUsername(), user.getId());
        }
        throw new BadCredentialsException("Password is incorrect");
    }

    @Override
    @Transactional
    public List<UserDto> getUsersForClient(final String clientId, final Pageable pageable) {
        return userServiceRepository.getAllByClientId(clientId, pageable)
                .map(entity -> modelMapper.map(entity, UserDto.class)).collect(Collectors.toList());
    }

    private String getRoles(final ClientDetails clientDetails) {
        return StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }
}
