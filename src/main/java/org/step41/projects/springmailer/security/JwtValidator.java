package org.step41.projects.springmailer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.step41.projects.springmailer.entities.JwtUser;

@Component
public class JwtValidator {

    @Value("${springmailer.api.secret")
    private String springMailerApiSecret;

    public JwtUser validate(String token) {
        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(springMailerApiSecret)
                    .parseClaimsJws(token)
                    .getBody();
            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setName((String) body.get("name"));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return jwtUser;
    }

}
