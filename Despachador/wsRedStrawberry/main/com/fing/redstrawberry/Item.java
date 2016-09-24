
package main.com.fing.redstrawberry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fechaHora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorproducto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="identificadortransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", propOrder = {
    "cantidad",
    "fechaHora",
    "identificadorproducto",
    "identificadortransaccion"
})
public class Item {

    protected Integer cantidad;
    protected String fechaHora;
    protected Long identificadorproducto;
    protected String identificadortransaccion;

    /**
     * Gets the value of the cantidad property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidad(Integer value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the fechaHora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the value of the fechaHora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHora(String value) {
        this.fechaHora = value;
    }

    /**
     * Gets the value of the identificadorproducto property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdentificadorproducto() {
        return identificadorproducto;
    }

    /**
     * Sets the value of the identificadorproducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdentificadorproducto(Long value) {
        this.identificadorproducto = value;
    }

    /**
     * Gets the value of the identificadortransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadortransaccion() {
        return identificadortransaccion;
    }

    /**
     * Sets the value of the identificadortransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadortransaccion(String value) {
        this.identificadortransaccion = value;
    }

}
