package com.myblog.springbootblogdemo.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.myblog.springbootblogdemo.dto.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

@Api(value = "/greet", description = "Saludos")
@RestController
@RequestMapping("/greet")
public class GreetController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "Saludar", notes = "Devuelve un saludo", authorizations = { @Authorization(value = "JWT") })
    @GetMapping(path = "/greeting")
    public Greeting greeting(
            @ApiParam(example = "Julian") @RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}