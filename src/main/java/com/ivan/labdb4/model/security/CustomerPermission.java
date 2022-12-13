package com.ivan.labdb4.model.security;

public enum CustomerPermission {

    HIGHLIGHT_READ("highlight:read"),
    HIGHLIGHT_WRITE("highlight:write");

    private final String permission;

    CustomerPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
