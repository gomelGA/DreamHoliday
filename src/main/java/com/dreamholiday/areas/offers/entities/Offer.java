package com.dreamholiday.areas.offers.entities;


import com.dreamholiday.areas.orders.entities.Order;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "offers")
@Transactional
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @NotNull
    private String destination;

    @Basic
    private String location;

    @Basic
    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "offer_conditions", columnDefinition = "TEXT")
    @NotNull
    private String offerConditions;

    @Column(name = "regular_price", columnDefinition = "DECIMAL(6,2) UNSIGNED")
    @NotNull
    private Double regularPrice;

    @Column(columnDefinition = "DECIMAL(4,2) UNSIGNED")
    private Double discount;

    @Column(name = "final_price", columnDefinition = "DECIMAL(6,2) UNSIGNED")
    @NotNull
    private Double finalPrice;

    @Column(name = "price_including_info", columnDefinition = "TEXT")
    @NotNull
    private String priceIncludingInfo;

    @Column(name = "start_date")
    @NotNull
    private Date startDate;

    @Column(name = "end_date")
    @NotNull
    private Date endDate;

    @ElementCollection()
    private List<String> pictures;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @Column(name = "bought_vouchers_count", columnDefinition = "INT UNSIGNED")
    private Integer boughtVouchersCount;

    public Offer() {
        this.setPictures(new ArrayList<String>());
        this.setOrders(new HashSet<Order>());
    }

    public Offer(String destination, String location, String title, String description, String shortDescription, String offerConditions, Double regularPrice, String priceIncludingInfo, Date startDate, Date endDate, List<String> pictures, Double discount, Integer boughtVouchersCount, Set<Order> orders) {

        this.setDestination(destination);
        this.setLocation(location);
        this.setTitle(title);
        this.setDescription(description);
        this.setShortDescription(shortDescription);
        this.setOfferConditions(offerConditions);
        this.setRegularPrice(regularPrice);
        this.setDiscount(discount);
        this.setPriceIncludingInfo(priceIncludingInfo);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setPictures(pictures);
        this.setBoughtVouchersCount(boughtVouchersCount);
        this.setOrders(orders);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setRegularPrice(Double price) {
        this.regularPrice = price;
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

    public void setFinalPrice(Double promotionalPrice) {
        this.finalPrice = promotionalPrice;
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

    public void addPictureUrl(String pictureUrl) {
        this.getPictures().add(pictureUrl);
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
