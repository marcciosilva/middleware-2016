package com.fing.Domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by jmsmuy on 19/08/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtoOrderItems {

    @XmlElement
    private Integer itemNumber;     //Numero de Item
    @XmlElement
    private Integer category;       //Categoria
    @XmlElement
    private Integer productId;      //Id Producto
    @XmlElement
    private String description;     //Descripcion producto
    @XmlElement
    private Integer amount;         //Cantidad
    @XmlElement
    private Double price;           //Precio

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DtoOrderItems{" +
                "itemNumber=" + itemNumber +
                ", category=" + category +
                ", productId=" + productId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
