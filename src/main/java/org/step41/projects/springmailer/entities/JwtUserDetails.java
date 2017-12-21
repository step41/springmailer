package org.step41.projects.springmailer.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String name;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(long id, String username, String name, String token, List<GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.token = token;
        this.authorities = grantedAuthorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

}
