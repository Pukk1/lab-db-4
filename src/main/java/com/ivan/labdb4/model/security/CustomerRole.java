package com.ivan.labdb4.model.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum CustomerRole {
    DEFAULT(Set.of(CustomerPermission.HIGHLIGHT_READ)),
    CREATOR(Set.of(CustomerPermission.HIGHLIGHT_WRITE, CustomerPermission.HIGHLIGHT_READ));

    private final Set<CustomerPermission> permissions;

    CustomerRole(Set<CustomerPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<CustomerPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
