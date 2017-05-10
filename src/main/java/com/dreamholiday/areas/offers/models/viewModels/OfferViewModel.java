package com.dreamholiday.areas.offers.models.viewModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferViewModel implements Serializable {

    private Long id;

    private String title;

    private String description;

    private String shortDescription;

    private String offerConditions;

    private Double regularPrice;

    private Double discount;

    private Double finalPrice;

    private String priceIncludingInfo;

    private Date startDate;

    private Date endDate;

    private List<String> pictures;

    private Integer boughtVouchersCount;

    public OfferViewModel() {
        this.setPictures(new ArrayList<String>());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getOfferConditions() {
        return this.offerConditions;
    }

    public void setOfferConditions(String offerConditions) {
        this.offerConditions = offerConditions;
    }

    public Double getRegularPrice() {
        return this.regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getPriceIncludingInfo() {
        return this.priceIncludingInfo;
    }

    public void setPriceIncludingInfo(String priceIncludingInfo) {
        this.priceIncludingInfo = priceIncludingInfo;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getPictures() {
        return this.pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public Integer getBoughtVouchersCount() {
        return this.boughtVouchersCount;
    }

    public void setBoughtVouchersCount(Integer boughtVouchersCount) {
        this.boughtVouchersCount = boughtVouchersCount;
    }
}
