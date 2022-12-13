package com.ivan.labdb4.model.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
