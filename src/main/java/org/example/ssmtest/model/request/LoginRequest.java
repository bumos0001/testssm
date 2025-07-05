package org.example.ssmtest.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String key;
    private String code;
}
