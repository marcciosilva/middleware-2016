
package com.fing.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anularPago complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="anularPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idConfirmacionPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anularPago", propOrder = {
    "idConfirmacionPago"
})
public class AnularPago {

    protected String idConfirmacionPago;

    /**
     * Gets the value of the idConfirmacionPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdConfirmacionPago() {
        return idConfirmacionPago;
    }

    /**
     * Sets the value of the idConfirmacionPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdConfirmacionPago(String value) {
        this.idConfirmacionPago = value;
    }

}
