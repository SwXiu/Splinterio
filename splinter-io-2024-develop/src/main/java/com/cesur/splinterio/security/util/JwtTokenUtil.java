package com.cesur.splinterio.security.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cesur.splinterio.security.model.CustomUserDetail;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(CustomUserDetail userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .claim("email", userDetails.getUsername())
                .claim("name", userDetails.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
