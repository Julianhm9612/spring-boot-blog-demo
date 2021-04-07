package com.myblog.springbootblogdemo.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.myblog.springbootblogdemo.dto.Login;
import com.myblog.springbootblogdemo.dto.Token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {

    @Value("${security.secret}")
    private String secret;

    public Token login(Login login) {
        // TODO: Lógica para validar usuario y contraseña
        String tokenJwt = getJWTToken(login.getUsername());
        Token token = new Token();
        token.setUser(login.getUsername());
        token.setToken(tokenJwt);
        return token;
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder().setId("example").setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
        return token;
    }
}
