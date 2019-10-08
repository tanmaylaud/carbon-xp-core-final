package com.db.Transaction;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaction")
public class Transaction {

    private String accountNumber;
    private double carbonBalance;
    private double creditDebitCarbonAmount;
    private String transactionType;
    private double transactionId;
    private String date;
    private String description;
    private String category;


    public double getCarbonBalance() {
        return carbonBalance;
    }

    public void setCarbonBalance(double carbonBalance) {
        this.carbonBalance = carbonBalance;
    }

    public double getCreditDebitCarbonAmount() {
        return creditDebitCarbonAmount;
    }

    public void setCreditDebitCarbonAmount(double creditDebitCarbonAmount) {
        this.creditDebitCarbonAmount = creditDebitCarbonAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(double transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
