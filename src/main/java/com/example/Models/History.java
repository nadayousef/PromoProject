package com.example.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class History {
 @Id
   private String msisdn;
  private String offer_ids ;
private LocalDate date;
   private LocalDate end_date;
private int capping;


   public String getMsisdn() {
      return msisdn;
   }

   public void setMsisdn(String msisdn) {
      this.msisdn = msisdn;
   }

   public String getOfferIds() {
      return offer_ids;
   }

   public void setOfferIds(String offerIds) {
      this.offer_ids = offerIds;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public LocalDate getEnd_date() {
      return end_date;
   }

   public void setEnd_date(LocalDate end_date) {
      this.end_date = end_date;
   }

   public int getCapping() {
      return capping;
   }

   public void setCapping(int capping) {
      this.capping = capping;
   }

}
