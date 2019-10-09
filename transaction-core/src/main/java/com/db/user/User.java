package com.db.user;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

   // @Id
    private String accountNumber;

    private String userName;
    private String electricityID;
    private String mobileNumber;
    private String panCardNumber;
    private String gasPipeLineId;
    private String emailId;
    //private double carbonBalance;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getElectricityID() {
        return electricityID;
    }

    public void setElectricityID(String electricityID) {
        this.electricityID = electricityID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getGasPipeLineId() {
        return gasPipeLineId;
    }

    public void setGasPipeLineId(String gasPipeLineId) {
        this.gasPipeLineId = gasPipeLineId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountNumber='" + accountNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", electricityID='" + electricityID + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", panCardNumber='" + panCardNumber + '\'' +
                ", gasPipeLineId='" + gasPipeLineId + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
