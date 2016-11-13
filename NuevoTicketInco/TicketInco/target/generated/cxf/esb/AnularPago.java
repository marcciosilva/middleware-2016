
package esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anularPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="anularPago"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idConfirmacionPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Obtiene el valor de la propiedad idConfirmacionPago.
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
     * Define el valor de la propiedad idConfirmacionPago.
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
