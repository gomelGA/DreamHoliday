package com.dreamholiday.areas.orders.models.bindingModels;

public class AddOrderBindingModels {

    private Integer voucherCount;

    private String paymentMethod;

    private String notes;

    public Integer getVoucherCount() {
        return this.voucherCount;
    }

    public void setVoucherCount(Integer voucherCount) {
        this.voucherCount = voucherCount;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
