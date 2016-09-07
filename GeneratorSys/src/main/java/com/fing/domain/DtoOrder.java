package com.fing.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jmsmuy on 19/08/16.
 */
@XmlRootElement
public class DtoOrder {

    /**
     * Datos entrada
     */


    private int orderNumber;            //Numero de orden
    private Date creationDate;          //Fecha y Hora de creacion
    private int customerId;             //Identificador del cliente
    private String paymentMethod;       //Forma de Pago

    /**
     * Facturación
     */

    private Double totalAmount;         //Monto
    private int currency;               //Moneda
    private Integer installments;       //Cuotas

    /**
     * Items
     */

    private List<DtoOrderItems> itemsList; //Items

    public int getOrderNumber() {
        return orderNumber;
    }

    @XmlAttribute
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

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public List<DtoOrderItems> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<DtoOrderItems> itemsList) {
        this.itemsList = itemsList;
    }

    /**
     * Agrega un item a la lista de items
     * En caso de que la lista sea null, además se crea la misma
     *
     * @param item
     */
    public void addItemToList(DtoOrderItems item) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        itemsList.add(item);
    }

    @Override
    public String toString() {
        return "DtoOrder{" +
                "orderNumber=" + orderNumber +
                ", creationDate=" + creationDate +
                ", customerId=" + customerId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", currency=" + currency +
                ", installments=" + installments +
                ", itemsList=" + itemsList.toString() +
                '}';
    }
}
