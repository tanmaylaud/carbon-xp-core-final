package com.community.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;

@Document
public class CommunityUsers implements Comparator {

    private String name;
    private String accountNumber;
    private String emailId;
    private Integer rankInGroup;

    public Integer getRankInGroup() {
        return rankInGroup;
    }

    public CommunityUsers setRankInGroup(Integer rankInGroup) {
        this.rankInGroup = rankInGroup;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public CommunityUsers setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

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

    @Override
    public int compare(Object o1, Object o2) {
        CommunityUsers user1 = (CommunityUsers) o1;
        CommunityUsers user2 = (CommunityUsers) o2;
        if(user1.getCarbonBalance() < user2.getCarbonBalance()){
            return 1;
        }
        else if (user1.getCarbonBalance() == user2.getCarbonBalance()){
            return 0;
        }
        else {
            return -1;
        }
    }
}
