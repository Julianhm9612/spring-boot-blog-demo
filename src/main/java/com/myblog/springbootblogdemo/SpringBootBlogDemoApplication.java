package com.myblog.springbootblogdemo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBlogDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogDemoApplication.class, args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
	}

}
