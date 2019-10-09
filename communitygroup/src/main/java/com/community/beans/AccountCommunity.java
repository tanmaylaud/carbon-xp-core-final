package com.community.beans;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccountCommunity {

    private String accountNumber;
    private String communityId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountCommunity setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getCommunityId() {
        return communityId;
    }

    public AccountCommunity setCommunityId(String communityId) {
        this.communityId = communityId;
        return this;
    }
}
