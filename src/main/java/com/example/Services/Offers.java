package com.example.Services;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.Exceptions.EX1;
import com.example.Exceptions.MyException;
import com.example.Models.*;
import com.example.Repositories.OffersHistory;
import com.example.Repositories.OffersInfo;
import com.example.Repositories.SegmentOfferRepo;
import com.example.Repositories.SegmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class Offers {
    private final SegmentRepo segmentRepo;
    @Autowired
    public SegmentOfferRepo segmentOfferRepo;
    @Autowired
    public OffersHistory offersHistory;
    @Autowired
    public OffersInfo offersInfo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Offers(SegmentRepo segmentRepo) {
        this.segmentRepo = segmentRepo;
    }

    public int getSegmentId(String msisdn) {

        Optional<Segmentation> segmentation = segmentRepo.findById(msisdn);
        if (segmentation.isEmpty()) {

        }

        int segmentationId = segmentation.get().getSegmentation_id();

        return segmentationId;
    }

    public String getOfferID(int segmentId) throws EX1 {

        Optional<SegmentationOffers> segmentationOffers = segmentOfferRepo.findById(segmentId);
        if (segmentationOffers.isEmpty()) {
            System.out.println("the segment offerID equals =");
            throw new EX1();
        }
        return segmentationOffers.get().getOffer_id();

    }

    public Offer getOfferInfo(String offerId) {

        if (offersInfo.findById(offerId).isPresent()) {
            return offersInfo.findById(offerId).get();
        } else {
            return null;
        }

    }

    public Boolean alreadyOptedIn(String msisdn, String offerId) {
        Optional<History> history = offersHistory.findById(msisdn);
        if (history.isEmpty()) {
            System.out.println("there is no taken offers to this dial");
            return false;
        }
        String dialOffers = history.get().getOfferIds();
        System.out.println(dialOffers);
        int index = dialOffers.indexOf(offerId);
        System.out.println(index);
        return index != -1;
    }

    public Boolean offerAvailable(String msisdn, String offerId) {
        Optional<History> history = offersHistory.findById(msisdn);
        if (LocalDate.now().isAfter(history.get().getEnd_date())) {
            System.out.println("Sorry the offer duration is expired ");
            return false;
        } else if (history.get().getCapping() == 0) {
            System.out.println("Sorry the offer count is finished");
            return false;
        }
        return true;
    }

    public void redemption(String msisdn, String offerId) {
        Offer offer = offersInfo.findById(offerId).get();
        if (alreadyOptedIn(msisdn, offerId)) {
            if (offerAvailable(msisdn, offerId)) {
                History history = offersHistory.findById(msisdn).get();

                jdbcTemplate.update("update  History set capping=? where msisdn=?", history.getCapping() - 1, msisdn);
                System.out.println("the offer is added successfully again ");
            } else System.out.println("offer not available");
        } else {
            LocalDate date = LocalDate.now();
            LocalDate endDate = date.plusDays(offer.capping_duration());
            jdbcTemplate.update("insert into History values (?,?,?,?,?)", msisdn, offerId, date, endDate, offer.capping()-1);
            System.out.println("the offer is added successfully for the first time ");
        }

    }
}
