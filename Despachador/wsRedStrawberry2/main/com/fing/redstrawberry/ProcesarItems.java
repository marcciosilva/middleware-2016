
package main.com.fing.redstrawberry;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcesarItems complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcesarItems">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaItem" type="{http://redstrawberry.fing.com.main/}item" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcesarItems", propOrder = {
    "listaItem"
})
public class ProcesarItems {

    @XmlElement(name = "ListaItem", nillable = true)
    protected List<Item> listaItem;

    /**
     * Gets the value of the listaItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getListaItem() {
        if (listaItem == null) {
            listaItem = new ArrayList<Item>();
        }
        return this.listaItem;
    }

}
