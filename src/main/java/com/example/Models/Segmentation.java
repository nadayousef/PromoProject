package com.example.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name ="SEGMENTATION")
public class Segmentation {
    @Id
    @Column
    private String msisdn;
    @Column
    private int segmentation_id;



    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getSegmentation_id() {
        return segmentation_id;
    }

    public void setSegmentation_id(int segmentation_id) {
        this.segmentation_id = segmentation_id;
    }

}
