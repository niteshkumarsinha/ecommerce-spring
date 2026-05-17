package com.codingpractice.ecommerce.security.response;

import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String jwtToken;
    private String username;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, List<String> roles, String jwtToken) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }
}
