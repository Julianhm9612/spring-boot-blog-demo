package com.myblog.springbootblogdemo.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myblog.springbootblogdemo.dao.UserServiceRepository;

public class AlreadyRegisteredEmailValidator implements ConstraintValidator<AlreadyRegisteredEmail, String> {
    private final UserServiceRepository userServiceRepository;

    @Autowired
    public AlreadyRegisteredEmailValidator(final UserServiceRepository userServiceRepository) {
        this.userServiceRepository = userServiceRepository;
    }

    @Override
    public boolean isValid(final String emailAddress, final ConstraintValidatorContext context) {
        return !userServiceRepository.existsByEmailAddress(emailAddress);
    }
}
