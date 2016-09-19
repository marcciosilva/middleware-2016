package com.fing.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Juan Manuel on 9/18/2016.
 */
public class DtoOrderItemsWithOrderInfo {
    private int orderNumber;
    private Date creationDate;
    private int customerId;
    private String paymentMethod;
    private Double totalAmount;
    private int currency;
    private Integer intallments;
    private List<DtoOrderItems> itemsList;

    public DtoOrderItemsWithOrderInfo() {
    }
    
    public DtoOrderItemsWithOrderInfo(DtoOrderItems item){
        this.itemsList = new ArrayList<>();
        this.itemsList.add(item);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public Integer getIntallments() {
        return intallments;
    }

    public void setIntallments(Integer intallments) {
        this.intallments = intallments;
    }

    public static DtoOrderItemsWithOrderInfo toDtoOrderItemsWithOrderInfo(DtoOrder order, DtoOrderItems item) {
        DtoOrderItemsWithOrderInfo dto = new DtoOrderItemsWithOrderInfo(item);
        dto.getOrderValues(order);
        return dto;
    }

    private void getOrderValues(DtoOrder order) {
        this.creationDate = order.getCreationDate();
        this.currency = order.getCurrency();
        this.customerId = order.getCustomerId();
        this.intallments = order.getInstallments();
        this.orderNumber = order.getOrderNumber();
        this.paymentMethod = order.getPaymentMethod();
        this.totalAmount = order.getTotalAmount();
    }

    public List<DtoOrderItems> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<DtoOrderItems> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return "DtoOrderItemsWithOrderInfo{" +
                "orderNumber=" + orderNumber +
                ", creationDate=" + creationDate +
                ", customerId=" + customerId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", currency=" + currency +
                ", intallments=" + intallments +
                '}';
    }
}
