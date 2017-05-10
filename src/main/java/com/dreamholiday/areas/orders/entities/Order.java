package com.dreamholiday.areas.orders.entities;

import com.dreamholiday.areas.users.entities.User;
import com.dreamholiday.areas.offers.entities.Offer;
import com.dreamholiday.areas.orders.enums.OrderStatus;
import com.dreamholiday.areas.orders.enums.PaymentMethod;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotNull
    @Column(name = "is_paid")
    private Boolean isPaid;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(name = "single_voucher_price")
    private Double singleVoucherPrice;

    @NotNull
    @Column(name = "voucher_count")
    private Integer voucherCount;

    @Basic
    private Double totalPrice;

    @Basic
    private String notes;

    public Order() {
    }

    public Order(Integer voucherCount, User user, Date creationDate, Offer offer, OrderStatus orderStatus, Boolean isPaid, PaymentMethod paymentMethod, Double singleVoucherPrice, Double totalPrice, String notes) {
        this.setVoucherCount(voucherCount);
        this.setUser(user);
        this.setCreationDate(creationDate);
        this.setOffer(offer);
        this.setOrderStatus(orderStatus);
        this.setPaid(isPaid);
        this.setPaymentMethod(paymentMethod);
        this.setSingleVoucherPrice(singleVoucherPrice);
        this.setTotalPrice(totalPrice);
        this.setNotes(notes);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoucherCount() {
        return this.voucherCount;
    }

    public void setVoucherCount(Integer number) {
        this.voucherCount = number;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Offer getOffer() {
        return this.offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getPaid() {
        return this.isPaid;
    }

    public void setPaid(Boolean paid) {
        this.isPaid = paid;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getSingleVoucherPrice() {
        return this.singleVoucherPrice;
    }

    public void setSingleVoucherPrice(Double price) {
        this.singleVoucherPrice = price;
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
