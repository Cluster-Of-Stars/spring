package com.codereview.codereview.global.model.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserAuthentication extends AbstractAuthenticationToken {

    private final AuthPayload authPayload;

    public UserAuthentication(Long userId) {
        super(null);
        this.authPayload = new AuthPayload(userId);
        setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public AuthPayload getPrincipal() {
        return authPayload;
    }

}