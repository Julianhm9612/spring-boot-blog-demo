package com.myblog.springbootblogdemo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.myblog.springbootblogdemo.dto.validation.SupportedAuthorities;
import com.myblog.springbootblogdemo.dto.validation.SupportedGrantTypes;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static com.myblog.springbootblogdemo.dto.PasswordRestrictions.ERROR_MESSAGE_PASSWORD;
import static com.myblog.springbootblogdemo.dto.PasswordRestrictions.REGEX_ENFORCER;

@ApiModel(description = "Transfer object that encapsulates client information")
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientDto {

    private static final int ONE_WEEk_VALIDITY = 604800;
    private static final int ONE_DAY_VALIDITY = 93600;

    @ApiModelProperty(value = "Client identifier, example: my-trusted-client")
    @NotNull
    @Size(min = 5, max = 255)
    private String clientId;

    @ApiModelProperty(position = 1, value = "Not important, they can be null")
    private String resourcedIds;

    @ApiModelProperty(position = 2, value = ERROR_MESSAGE_PASSWORD)
    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = REGEX_ENFORCER, message = ERROR_MESSAGE_PASSWORD)
    private String clientSecret;

    @ApiModelProperty(position = 3, value = "Comma separated values, example:  read, write")
    @NotNull
    @Size(min = 4, max = 255)
    private String scope;

    @ApiModelProperty(position = 4, value = "password, refresh_token  & others, see oauth2 standard")
    @NotNull
    @SupportedGrantTypes
    @Size(min = 5, max = 255)
    private String authorizedGrantTypes;

    @ApiModelProperty(position = 5, value = "can be null")
    private String webServerRedirectUri;

    @ApiModelProperty(position = 6, value = "can be null")
    @NotNull
    @Size(min = 5, max = 255)
    @SupportedAuthorities
    private String authorities;

    @ApiModelProperty(position = 7)
    @NotNull
    @Min(ONE_DAY_VALIDITY)
    private int accessTokenValidity;

    @ApiModelProperty(position = 8)
    @NotNull
    @Min(ONE_WEEk_VALIDITY)
    private int refreshTokenValidity;

    @ApiModelProperty(position = 9)
    private String additionalInformation;

    @ApiModelProperty(position = 10)
    private String autoApproveScopes;

}
