package com.offer.mgmt.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WalletOfferStatus {

    private String accountNumber;
    private Long offerId;
    String  offerStatus;

    public String getAccountNumber() {
        return accountNumber;
    }

    public WalletOfferStatus setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Long getOfferId() {
        return offerId;
    }

    public WalletOfferStatus setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public String setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
        return this.offerStatus;
    }
}
