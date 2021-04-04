package com.myblog.springbootblogdemo.dto;

import java.math.BigInteger;
import java.time.ZonedDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.myblog.springbootblogdemo.dto.validation.AlreadyExistingUsername;
import com.myblog.springbootblogdemo.dto.validation.AlreadyRegisteredEmail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static com.myblog.springbootblogdemo.controller.ApiModelPropertyValues.ONLY_FOR_GET_MESSAGE;

import static com.myblog.springbootblogdemo.dto.PasswordRestrictions.ERROR_MESSAGE_PASSWORD;
import static com.myblog.springbootblogdemo.dto.PasswordRestrictions.REGEX_ENFORCER;

@ApiModel(description = "Transfer object that encapsulates  user information")
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    @ApiModelProperty(hidden = true, value = ONLY_FOR_GET_MESSAGE)
    private BigInteger id;

    @ApiModelProperty(required = true, position = 1)
    @NotNull
    @Size(min = 5, max = 255)
    @Email
    @AlreadyRegisteredEmail
    private String emailAddress;

    @ApiModelProperty(position = 2, required = true)
    @NotNull
    @Size(min = 5, max = 255)
    @AlreadyExistingUsername
    private String username;

    @ApiModelProperty(position = 3, required = true)
    @NotNull
    @Size(min = 2, max = 255)
    private String firstName;

    @ApiModelProperty(position = 4, required = true)
    @NotNull
    @Size(min = 2, max = 255)
    private String lastName;

    @ApiModelProperty(position = 5, required = true)
    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = REGEX_ENFORCER, message = ERROR_MESSAGE_PASSWORD)
    private String password;

    @ApiModelProperty(position = 6, required = true)
    @NotNull
    @Size(min = 5, max = 255)
    private String clientId;

    @ApiModelProperty(position = 7)
    @Null
    private ZonedDateTime creationTime;

    @ApiModelProperty(position = 8)
    @Null
    private ZonedDateTime updateTime;

    @ApiModelProperty(position = 9, required = true)
    @Size(min = 8, max = 255)
    private String oldPassword;

}
