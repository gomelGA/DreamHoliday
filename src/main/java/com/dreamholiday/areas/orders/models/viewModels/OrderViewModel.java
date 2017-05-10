package com.dreamholiday.areas.orders.models.viewModels;

import java.io.Serializable;
import java.util.Date;

public class OrderViewModel implements Serializable {

    private Long id;

    private Date creationDate;

    private String orderStatus;

    private String offerTitle;

    private Long offerId;

    private Double offerRegularPrice;

    private Boolean isPaid;

    private String paymentMethod;

    private Double singleVoucherPrice;

    private Integer voucherCount;

    private Double totalPrice;

    private String notes;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOfferTitle() {
        return this.offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public Long getOfferId() {
        return this.offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Double getOfferRegularPrice() {
        return this.offerRegularPrice;
    }

    public void setOfferRegularPrice(Double offerRegularPrice) {
        this.offerRegularPrice = offerRegularPrice;
    }

    public Boolean getPaid() {
        return this.isPaid;
    }

    public void setPaid(Boolean paid) {
        this.isPaid = paid;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getSingleVoucherPrice() {
        return this.singleVoucherPrice;
    }

    public void setSingleVoucherPrice(Double singleVoucherPrice) {
        this.singleVoucherPrice = singleVoucherPrice;
    }

    public Integer getVoucherCount() {
        return this.voucherCount;
    }

    public void setVoucherCount(Integer voucherCount) {
        this.voucherCount = voucherCount;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
