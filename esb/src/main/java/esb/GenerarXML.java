package esb;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerarXML {

	public DOMSource GenerarXMLEnviar(String nroTarjeta, String fechaVencimiento, String digitoVerificador, String monto)
	{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("confirmacion");
			doc.appendChild(rootElement);

			Element nroTarjetaElement = doc.createElement("nroTarjeta");
			nroTarjetaElement.appendChild(doc.createTextNode(nroTarjeta));
			rootElement.appendChild(nroTarjetaElement);
			
			Element fechaVencimientoElement = doc.createElement("fechaVencimiento");
			fechaVencimientoElement.appendChild(doc.createTextNode(fechaVencimiento));
			rootElement.appendChild(fechaVencimientoElement);
			
			Element digitoVerificadorElement = doc.createElement("digitoVerificador");
			digitoVerificadorElement.appendChild(doc.createTextNode(digitoVerificador));
			rootElement.appendChild(digitoVerificadorElement);
			
			Element montoElement = doc.createElement("monto");
			montoElement.appendChild(doc.createTextNode(monto));
			rootElement.appendChild(montoElement);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);			
			
			return source;
			
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return null;
		}
	
	public DOMSource GenerarXMLAnular(String idConfirmacionPago)
	{
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("anulacion");
			doc.appendChild(rootElement);

			Element idConfirmacionPagoElement = doc.createElement("idConfirmacionPago");
			idConfirmacionPagoElement.appendChild(doc.createTextNode(idConfirmacionPago));
			rootElement.appendChild(idConfirmacionPagoElement);	
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);			
			
			return source;
			
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return null;
		}

}
