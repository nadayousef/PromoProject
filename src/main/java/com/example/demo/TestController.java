package com.example.demo;

import com.example.Exceptions.EX1;
import com.example.Exceptions.MyException;
import com.example.Models.Offer;
import com.example.Services.Offers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    public Offers offersService;
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/ussd/hello/{name}")
    public TestRecord hello(@PathVariable String name, @RequestParam Integer age) {
        logger.info("Hello from API :D");
        return new TestRecord(name, age);
    }

    record TestRecord(String name, Integer age) {
    }

    @GetMapping("/getsegmentId/{msisdn}")
    public void getSegmenID(@PathVariable String msisdn) {
        int segmentId = offersService.getSegmentId(msisdn);
        System.out.println("the segment id equals =");
        System.out.println(segmentId);

    }

    @GetMapping("/getOfferId/{msisdn}")
    public String getOfferID(@PathVariable String msisdn) {
        int segmentId = offersService.getSegmentId(msisdn);
        System.out.println("the segment id equals =");
        System.out.println(segmentId);
        String offerId = "";
        try {
            offerId = offersService.getOfferID(segmentId);
        } catch (MyException e) {
            return e.getErrorCode();

        }

        return offerId;

    }

    @GetMapping("/inquiry/{msisdn}")
    public Offer inquiry(@PathVariable String msisdn) throws MyException {
        int segmentId = offersService.getSegmentId(msisdn);
        System.out.println("the segment id equals =");
        System.out.println(segmentId);
        String offerId = "";
        try {
            offerId = offersService.getOfferID(segmentId);
        } catch (MyException e) {
            System.out.println("offer id not found");
            throw new MyException("sjsjs","hhshs");
        }
        Offer offer=offersService.getOfferInfo(offerId);
        return offer;

    }
    @GetMapping("/redeem/{msisdn}/{offerId}")
    public void redeemOffer(@PathVariable String msisdn,@PathVariable String offerId){
        offersService.redemption(msisdn,offerId);
    }
}
