package com.db.carbonXP;


import java.util.Date;
import java.util.Objects;

public class Offer {
    private double offerId;
    private String offerName;
    private String offerDescription;
    private int creditRating;
    private int offerValue;
    private Date validity;

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return offerId == offer.offerId &&
                offerValue == offer.offerValue &&
                Objects.equals(offerName, offer.offerName) &&
                Objects.equals(offerDescription, offer.offerDescription) &&
                Objects.equals(creditRating, offer.creditRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, offerName, offerDescription, creditRating, offerValue);
    }

    public double getOfferId() {
        return offerId;
    }

    public void setOfferId(double offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public int getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

    public int getOfferValue() {
        return offerValue;
    }

    public void setOfferValue(int offerValue) {
        this.offerValue = offerValue;
    }
}
