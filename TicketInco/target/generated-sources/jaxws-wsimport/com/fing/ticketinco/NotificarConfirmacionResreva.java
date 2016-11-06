
package com.fing.ticketinco;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NotificarConfirmacionResreva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotificarConfirmacionResreva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idReserva" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificarConfirmacionResreva", propOrder = {
    "idReserva",
    "arg1"
})
public class NotificarConfirmacionResreva {

    protected long idReserva;
    protected byte[] arg1;

    /**
     * Gets the value of the idReserva property.
     * 
     */
    public long getIdReserva() {
        return idReserva;
    }

    /**
     * Sets the value of the idReserva property.
     * 
     */
    public void setIdReserva(long value) {
        this.idReserva = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setArg1(byte[] value) {
        this.arg1 = value;
    }

}
