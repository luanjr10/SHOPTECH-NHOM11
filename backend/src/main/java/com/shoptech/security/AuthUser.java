package com.shoptech.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/** Principal gắn vào SecurityContext sau khi xác thực JWT. */
public record AuthUser(Long id, String role, String jti, long expiresAtEpochSeconds) {

    public boolean isAdmin() {
        return "admin".equals(role);
    }

    public boolean isEmployee() {
        return "employee".equals(role);
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }
}
