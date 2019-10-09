package com.community.beans;

public class CommunityUsers {

    private String name;
    private String accountNumber;
    private Double carbonBalance;

    public String getName() {
        return name;
    }

    public CommunityUsers setName(String name) {
        this.name = name;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public CommunityUsers setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Double getCarbonBalance() {
        return carbonBalance;
    }

    public CommunityUsers setCarbonBalance(Double carbonBalance) {
        this.carbonBalance = carbonBalance;
        return this;
    }
}
