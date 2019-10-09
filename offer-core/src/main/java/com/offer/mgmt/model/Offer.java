package com.offer.mgmt.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document
public class Offer{

    private Long offerId;
    private String offerName;
    private String offerDescription;
    private Date validity;
    private int offerValue;
    private int creditRating;

    public Offer() {
    }

    public Offer(Long offerId, Date validity, String offerName, String offerDescription,
                 int offerValue,
                 int creditBalance) {
        this.offerId = offerId;
        this.validity = validity;
        this.offerValue = offerValue;
        this.offerName = offerName;
        this.offerDescription =offerDescription;
        this.creditRating = creditRating;
    }


    public Long getOfferId() {
        return offerId;
    }

    public Date getValidity() {
        return validity;
    }

    public String getOfferName() {
        return offerName;
    }

    public Offer setOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public Offer setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
        return this;
    }

    public Offer setValidity(Date validity) {
        this.validity = validity;
        return this;
    }


    public int getOfferValue() {
        return offerValue;
    }

    public Offer setOfferValue(int offerValue) {
        this.offerValue = offerValue;
        return this;
    }

    public int getCreditRating() {
        return creditRating;
    }

    public Offer setCreditRating(int creditRating) {
        this.creditRating = creditRating;
        return this;
    }

    public Offer setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", offerName='" + offerName + '\'' +
                ", offerDescription='" + offerDescription + '\'' +
                ", validity=" + validity +
                ", offerValue=" + offerValue +
                ", creditRating=" + creditRating +
                '}';
    }
}
