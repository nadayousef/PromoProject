package com.example.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "SEGMENTATIONOFFERS")
public class SegmentationOffers {
    @Id
   @Column
   private int  segmentation_id;
   @Column
   private String offer_id;

    public int getSegmentation_id() {
        return segmentation_id;
    }

    public void setSegmentation_id(int segmentation_id) {
        this.segmentation_id = segmentation_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }
}
