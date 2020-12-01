package com.mherscode.minibank.model.dto;

public class GetUser {

    private String username;

    public GetUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
