package com.myblog.springbootblogdemo.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class Login {

    @ApiModelProperty(example = "example", notes = "Nombre del usuario al momento de registrarse")
    @NotNull(message = "El campo username es requerido")
    private String username;

    @ApiModelProperty(example = "example123")
    @NotNull(message = "El campo password es requerido")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
