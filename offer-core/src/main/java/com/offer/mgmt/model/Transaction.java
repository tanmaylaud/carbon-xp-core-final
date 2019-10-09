package com.offer.mgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="transaction")
@JsonIgnoreProperties
public class Transaction {

    private String accountNumber;
    private double carbonBalance;
    private double transactionId;
    private double creditDebitCarbonAmount;
    private String transactionType;
    private String date;
    private String description;
    private String category;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Transaction setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public double getCarbonBalance() {
        return carbonBalance;
    }

    public Transaction setCarbonBalance(double carbonBalance) {
        this.carbonBalance = carbonBalance;
        return this;
    }

    public double getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(double transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public double getCreditDebitCarbonAmount() {
        return creditDebitCarbonAmount;
    }

    public Transaction setCreditDebitCarbonAmount(double creditDebitCarbonAmount) {
        this.creditDebitCarbonAmount = creditDebitCarbonAmount;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Transaction setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Transaction setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Transaction setCategory(String category) {
        this.category = category;
        return this;
    }
}
