package com.example.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

public record Offer (
      @Id
         String offer_id,
         String offer_info,
         int capping,
          int capping_duration


){}
