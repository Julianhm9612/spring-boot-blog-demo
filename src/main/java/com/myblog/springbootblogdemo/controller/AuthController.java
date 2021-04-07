package com.myblog.springbootblogdemo.controller;

import javax.validation.Valid;

import com.myblog.springbootblogdemo.dto.Login;
import com.myblog.springbootblogdemo.dto.Token;
import com.myblog.springbootblogdemo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/auth", description = "Autenticación y seguridad")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "Login", notes = "Autenticación para obtener token")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Usuario incorrecto") })
    @PostMapping("login")
    public Token login(@Valid @RequestBody Login login) {
        return authService.login(login);
    }

}
