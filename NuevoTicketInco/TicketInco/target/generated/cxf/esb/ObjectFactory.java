
package esb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the esb package. 
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

    private final static QName _AnularPago_QNAME = new QName("http://esb/", "anularPago");
    private final static QName _AnularPagoResponse_QNAME = new QName("http://esb/", "anularPagoResponse");
    private final static QName _ConfirmarPago_QNAME = new QName("http://esb/", "confirmarPago");
    private final static QName _ConfirmarPagoResponse_QNAME = new QName("http://esb/", "confirmarPagoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: esb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnularPago }
     * 
     */
    public AnularPago createAnularPago() {
        return new AnularPago();
    }

    /**
     * Create an instance of {@link AnularPagoResponse }
     * 
     */
    public AnularPagoResponse createAnularPagoResponse() {
        return new AnularPagoResponse();
    }

    /**
     * Create an instance of {@link ConfirmarPago }
     * 
     */
    public ConfirmarPago createConfirmarPago() {
        return new ConfirmarPago();
    }

    /**
     * Create an instance of {@link ConfirmarPagoResponse }
     * 
     */
    public ConfirmarPagoResponse createConfirmarPagoResponse() {
        return new ConfirmarPagoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnularPago }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://esb/", name = "anularPago")
    public JAXBElement<AnularPago> createAnularPago(AnularPago value) {
        return new JAXBElement<AnularPago>(_AnularPago_QNAME, AnularPago.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnularPagoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://esb/", name = "anularPagoResponse")
    public JAXBElement<AnularPagoResponse> createAnularPagoResponse(AnularPagoResponse value) {
        return new JAXBElement<AnularPagoResponse>(_AnularPagoResponse_QNAME, AnularPagoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarPago }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://esb/", name = "confirmarPago")
    public JAXBElement<ConfirmarPago> createConfirmarPago(ConfirmarPago value) {
        return new JAXBElement<ConfirmarPago>(_ConfirmarPago_QNAME, ConfirmarPago.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarPagoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://esb/", name = "confirmarPagoResponse")
    public JAXBElement<ConfirmarPagoResponse> createConfirmarPagoResponse(ConfirmarPagoResponse value) {
        return new JAXBElement<ConfirmarPagoResponse>(_ConfirmarPagoResponse_QNAME, ConfirmarPagoResponse.class, null, value);
    }

}
