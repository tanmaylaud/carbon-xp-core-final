package com.offer.mgmt.service;

import com.offer.mgmt.model.Offer;
import com.offer.mgmt.model.Transaction;
import com.offer.mgmt.model.WalletOfferStatus;
import com.offer.mgmt.repository.OfferRepository;
import com.offer.mgmt.repository.TransactionRepository;

import com.offer.mgmt.repository.WalletOfferStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    WalletOfferStatusRepository walletOfferStatusRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Offer> getAllOffers(String accountNumber, Double carbonCredit) {
        List<Offer> offers = offerRepository.findAll();
        List<Offer> unclaimedOffers = new ArrayList<>();

        if(offers.size()>0) {
            offers=offers.stream().filter(x->x.getCreditRating()<carbonCredit && x.getValidity().after(new Date())).collect(Collectors.toList());
            List<WalletOfferStatus> walletOfferStatus = walletOfferStatusRepository.findByAccountNumber(accountNumber);
            unclaimedOffers= offers.stream().filter(x-> !walletOfferStatus.stream().anyMatch(w-> w.getOfferId().equals(x.getOfferId()))).collect(Collectors.toList());

        }
        return unclaimedOffers;
    }


    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Double getCarbonBalanceByAccountId(String accountNumber) {
        List<Transaction> optionalTransaction= transactionRepository.findByAccountNumber(accountNumber);

            Optional<Transaction> transaction =
                    optionalTransaction.stream().max(Comparator.comparing(Transaction::getTransactionId));

                return transaction.get().getCarbonBalance();
            }

    @Override
    public  WalletOfferStatus updateOffer (WalletOfferStatus walletOfferStatus) {

        return walletOfferStatusRepository.save(walletOfferStatus);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}



