package com.offer.mgmt.controller;

import com.offer.mgmt.model.Offer;
import com.offer.mgmt.model.Transaction;
import com.offer.mgmt.model.WalletOfferStatus;
import com.offer.mgmt.service.OfferService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    OfferService offerService;

    private static long transactionNumber=10000;


    @PostMapping
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    @GetMapping("/{accountNumber}")

    public List<Offer> getAllOffers(@PathVariable(value="accountNumber") String accountNumber) {
         Double carbonBalance=getCarbonBalance(accountNumber);
        return offerService.getAllOffers(accountNumber, carbonBalance);
    }
    private Double getCarbonBalance(String accountNumber) {
        return offerService.getCarbonBalanceByAccountId(accountNumber);
    }

    @PostMapping("/{accountNumber}")
    public ResponseEntity<String> updateOffer(@PathVariable String accountNumber ,
                                         @RequestBody WalletOfferStatus walletOfferStatus) {
        Double carbonBalance=getCarbonBalance(accountNumber);
        List<Offer> offers=offerService.getAllOffers(accountNumber,carbonBalance);
        Optional<Offer> offerOptional=
                offers.stream().filter(x->x.getOfferId().equals(walletOfferStatus.getOfferId())).findFirst();
        if(offerOptional.isPresent()) {
            if(offerOptional.get().getOfferValue() < carbonBalance) {

                Transaction transaction=new Transaction();
                transaction.setAccountNumber(accountNumber);
                transaction.setCategory(offerOptional.get().getOfferName());
                transaction.setDescription(offerOptional.get().getOfferDescription());
                transaction.setCreditDebitCarbonAmount(offerOptional.get().getOfferValue());
                transaction.setCarbonBalance(getCarbonBalance((accountNumber))-offerOptional.get().getOfferValue());
                transaction.setTransactionType("Debit");
                offerService.addTransaction(transaction);
                offerService.updateOffer(walletOfferStatus);

                return new ResponseEntity<>("Offer claimed ",HttpStatus.OK);
            }

            return new ResponseEntity<>("Carbon balance is less",HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Offer is invalid ",HttpStatus.BAD_REQUEST);

    }



}
