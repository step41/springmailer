package org.step41.projects.springmailer.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.step41.projects.springmailer.entities.JwtUser;

@Component
public class JwtGenerator {

    @Value("${springmailer.api.secret")
    private String springMailerApiSecret;

    public String generate(JwtUser jwtUser) {

        Claims claims = Jwts.claims().setSubject(jwtUser.getUsername());
        claims.put("id", String.valueOf(jwtUser.getId()));
        claims.put("name", jwtUser.getName());
        claims.put("role", jwtUser.getRole());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, springMailerApiSecret)
                .compact();

    }

}
