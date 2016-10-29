package esb;

import java.io.StringWriter;

import javax.jws.WebService;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@WebService(endpointInterface = "esb",
            serviceName = "MedioPagoLocal")
public class MedioPagoLocalImp implements MedioPagoLocal{
	public void confirmarPago(String nroTarjeta, String fechaVencimeinto, String digitoVerificador, String monto){
		GenerarXML generarXML = new GenerarXML();
		DOMSource xml = generarXML.GenerarXMLEnviar(nroTarjeta, fechaVencimeinto, digitoVerificador, monto);
		
		StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
		try {
			transformer = tf.newTransformer();
		
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	
	        try {
				transformer.transform(xml, new StreamResult(sw));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //return sw.toString();
		System.out.println(sw.toString());
	}
}
