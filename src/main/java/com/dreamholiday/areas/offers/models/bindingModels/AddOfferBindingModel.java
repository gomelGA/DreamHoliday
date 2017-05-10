package com.dreamholiday.areas.offers.models.bindingModels;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddOfferBindingModel {

    @NotBlank(message = "Offer destination required")
    private String destination;

    private String location;

    @NotBlank(message = "Offer title required")
    private String title;

    @NotBlank(message = "Offer description required")
    private String description;

    private String shortDescription;

    @NotBlank(message = "Offer conditions required")
    private String offerConditions;

    @DecimalMin(value = "0.00", message = "The price of offer can not be less than 0.00")
    private Double regularPrice;

    @DecimalMin(value = "0.00", message = "The discount can not be less than 0.00")
    private Double discount;

    @NotBlank(message = "Price including info required")
    private String priceIncludingInfo;

    @NotNull(message = "Starting date of offer required")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull(message = "Ending date of offer required")
    private Date endDate;

    @NotBlank(message = "Image URLs required")
    private String pictures;

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPictures() {
        return this.pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}
