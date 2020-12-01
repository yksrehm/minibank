package com.mherscode.minibank.model.dto;

public class GetRequestDto {

    private long id;
    private String username;
    private String rcpUserName;
    private double amount;

    public GetRequestDto() {
    }

    public GetRequestDto(long id, String username, String rcpUserName, double amount) {
        this.id = id;
        this.username = username;
        this.rcpUserName = rcpUserName;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRcpUserName() {
        return rcpUserName;
    }

    public void setRcpUserName(String rcpUserName) {
        this.rcpUserName = rcpUserName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
