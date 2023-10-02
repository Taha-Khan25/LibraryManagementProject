package com.example.LibraryManagement.DTO;

public class AuthenticationResponse {
    private String accessToken;

    public AuthenticationResponse(String token) {
        this.accessToken = token;
    }

    public AuthenticationResponse() {
    }

    public String getToken() {
        return accessToken;
    }

    public void setToken(String token) {
        this.accessToken = token;
    }

}