package org.step41.projects.springmailer.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.step41.projects.springmailer.entities.JwtAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.step41.projects.springmailer.config.Constants.Routing.URI_API_ENDPOINT_FILTER;
import static org.step41.projects.springmailer.config.Constants.Strings.MSG_JWT_NOT_FOUND;
import static org.step41.projects.springmailer.config.Constants.Strings.API_TOKEN_HEADER_NAME;
import static org.step41.projects.springmailer.config.Constants.Strings.API_TOKEN_HEADER_VALUE_PREFIX;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super(URI_API_ENDPOINT_FILTER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String header = httpServletRequest.getHeader(API_TOKEN_HEADER_NAME);
        if (header == null || !header.startsWith(API_TOKEN_HEADER_VALUE_PREFIX)) {
            throw new RuntimeException(MSG_JWT_NOT_FOUND);
        }
        String authenticationToken = header.substring(6);
        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
