package org.step41.projects.springmailer.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.step41.projects.springmailer.entities.JwtUser;
import org.step41.projects.springmailer.security.JwtGenerator;

import static org.step41.projects.springmailer.config.Constants.Routing.URI_TOKEN_ENDPOINT;

@RestController
@RequestMapping(URI_TOKEN_ENDPOINT)
public class TokensController {

    private JwtGenerator jwtGenerator;

    public TokensController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        return jwtGenerator.generate(jwtUser);
    }

}
