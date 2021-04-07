package com.myblog.springbootblogdemo.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/*";

    public static final Contact DEFAULT_CONTACT = new Contact("My Blog", "https://www.myblog.com",
            "soporte@myblog.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("My Blog API", "Documentation", "1.0", "",
            DEFAULT_CONTACT, "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
            Arrays.asList("application/json"));

    String message403 = "Ha ocurrido un error de permisos";
    String message500 = "Ha ocurrido un error no controlado en la aplicación";

    List<Response> messages = Arrays.asList(new ResponseBuilder().code("403").description(message403).build(),
            new ResponseBuilder().code("404").description("NotFound").build(),
            new ResponseBuilder().code("500").description(message500).build());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.myblog.springbootblogdemo")).build()
                .apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES).securitySchemes(Arrays.asList(apiKey()))
                .globalResponses(HttpMethod.GET, messages).globalResponses(HttpMethod.POST, messages)
                .globalResponses(HttpMethod.PUT, messages).globalResponses(HttpMethod.DELETE, messages);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

}
