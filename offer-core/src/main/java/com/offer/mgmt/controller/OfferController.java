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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    OfferService offerService;


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
   /* @GetMapping
    public Offer getOffer() {
        return new Offer(new Long(123), OfferStatus.UnClaimed,new Date(),"BookMyShow","",500,1000);
    }*/
    @PostMapping("/{accountNumber}")
    public ResponseEntity<String> updateOffer(@PathVariable String accountNumber ,
                                         @RequestBody WalletOfferStatus walletOfferStatus) {
        Double carbonBalance=getCarbonBalance(accountNumber);
        List<Offer> offers=offerService.getAllOffers(accountNumber,carbonBalance);
        Optional<Offer> offerOptional=
                offers.stream().filter(x->x.getOfferId().equals(walletOfferStatus.getOfferId())).findFirst();
        if(offerOptional.isPresent()) {
            if(offerOptional.get().getOfferValue() < carbonBalance) {

                offerService.updateOffer(walletOfferStatus);

                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("Carbon balance is less",HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {

        return offerService.addTransaction(transaction);
    }


}
