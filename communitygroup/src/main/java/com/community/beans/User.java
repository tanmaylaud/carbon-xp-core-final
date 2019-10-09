package com.community.beans;

public class User {

    private String accountNumber;

    private String userName;
    private String electricityID;
    private String mobileNumber;
    private String panCardNumber;
    private String gasPipeLineId;
    private String emailId;


    public String getAccountNumber() {
        return accountNumber;
    }

    public User setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getElectricityID() {
        return electricityID;
    }

    public User setElectricityID(String electricityID) {
        this.electricityID = electricityID;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public User setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public User setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
        return this;
    }

    public String getGasPipeLineId() {
        return gasPipeLineId;
    }

    public User setGasPipeLineId(String gasPipeLineId) {
        this.gasPipeLineId = gasPipeLineId;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public User setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }
}
