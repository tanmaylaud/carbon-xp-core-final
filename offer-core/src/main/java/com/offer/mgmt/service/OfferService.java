package com.offer.mgmt.service;

import com.offer.mgmt.model.Offer;
import com.offer.mgmt.model.Transaction;
import com.offer.mgmt.model.WalletOfferStatus;


import java.util.List;
import java.util.Optional;


public interface OfferService {

    List<Offer> getAllOffers(String accountNumber, Double carbonCredit);

    Offer createOffer(Offer offer);

    Double  getCarbonBalanceByAccountId(String accountNumber);

    WalletOfferStatus updateOffer(WalletOfferStatus walletOfferStatus);

    Transaction addTransaction(Transaction transaction);
}
