
package main.com.fing.redstrawberry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the main.com.fing.redstrawberry package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcesarItems_QNAME = new QName("http://redstrawberry.fing.com.main/", "ProcesarItems");
    private final static QName _ProcesarItemsResponse_QNAME = new QName("http://redstrawberry.fing.com.main/", "ProcesarItemsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: main.com.fing.redstrawberry
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link ProcesarItemsResponse }
     * 
     */
    public ProcesarItemsResponse createProcesarItemsResponse() {
        return new ProcesarItemsResponse();
    }

    /**
     * Create an instance of {@link ProcesarItems }
     * 
     */
    public ProcesarItems createProcesarItems() {
        return new ProcesarItems();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://redstrawberry.fing.com.main/", name = "ProcesarItems")
    public JAXBElement<ProcesarItems> createProcesarItems(ProcesarItems value) {
        return new JAXBElement<ProcesarItems>(_ProcesarItems_QNAME, ProcesarItems.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcesarItemsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://redstrawberry.fing.com.main/", name = "ProcesarItemsResponse")
    public JAXBElement<ProcesarItemsResponse> createProcesarItemsResponse(ProcesarItemsResponse value) {
        return new JAXBElement<ProcesarItemsResponse>(_ProcesarItemsResponse_QNAME, ProcesarItemsResponse.class, null, value);
    }

}
