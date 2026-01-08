package com.employee.dto;

import java.util.Set;

public class LoginResponse {
    private String token;
    private String username;
    private String email;
    private String message;
    private Set<String> roles;
    private Long employeeId;

    public LoginResponse() {}

    public LoginResponse(String token, String username, String email, String message) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.message = message;
    }

    public LoginResponse(String token, String username, String email, String message, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.message = message;
        this.roles = roles;
    }
    
    public LoginResponse(String token, String username, String email, String message, Set<String> roles, Long employeeId) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.message = message;
        this.roles = roles;
        this.employeeId = employeeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
