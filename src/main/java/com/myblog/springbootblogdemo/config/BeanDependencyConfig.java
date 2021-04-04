package com.myblog.springbootblogdemo.config;

import javax.sql.DataSource;

import com.myblog.springbootblogdemo.service.converters.ClientDtoConverter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

@Configuration
public class BeanDependencyConfig {
    private final DataSource dataSource;

    @Autowired
    public BeanDependencyConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        modelMapper.addConverter(new ClientDtoConverter());
        return modelMapper;
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public ClientRegistrationService clientRegistrationService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
