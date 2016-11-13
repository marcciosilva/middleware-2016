package esb;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.8
 * 2016-11-13T11:33:18.488-03:00
 * Generated source version: 3.1.8
 * 
 */
@WebServiceClient(name = "MedioPagoLocalService", 
                  wsdlLocation = "file:/C:/Users/Cami/Documents/Fing/Middleware/Obligatorio2/CodigoObligatorio2/NuevoTicketInco/TicketInco/src/main/wsdl/MedioPagoLocal.wsdl",
                  targetNamespace = "http://esb/") 
public class MedioPagoLocalService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://esb/", "MedioPagoLocalService");
    public final static QName MedioPagoLocalPort = new QName("http://esb/", "MedioPagoLocalPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Cami/Documents/Fing/Middleware/Obligatorio2/CodigoObligatorio2/NuevoTicketInco/TicketInco/src/main/wsdl/MedioPagoLocal.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(MedioPagoLocalService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Cami/Documents/Fing/Middleware/Obligatorio2/CodigoObligatorio2/NuevoTicketInco/TicketInco/src/main/wsdl/MedioPagoLocal.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public MedioPagoLocalService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MedioPagoLocalService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MedioPagoLocalService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public MedioPagoLocalService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public MedioPagoLocalService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public MedioPagoLocalService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns MedioPagoLocal
     */
    @WebEndpoint(name = "MedioPagoLocalPort")
    public MedioPagoLocal getMedioPagoLocalPort() {
        return super.getPort(MedioPagoLocalPort, MedioPagoLocal.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MedioPagoLocal
     */
    @WebEndpoint(name = "MedioPagoLocalPort")
    public MedioPagoLocal getMedioPagoLocalPort(WebServiceFeature... features) {
        return super.getPort(MedioPagoLocalPort, MedioPagoLocal.class, features);
    }

}
