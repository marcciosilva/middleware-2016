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

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("confirmacion");
			doc.appendChild(rootElement);

			// staff elements
			//Element staff = doc.createElement("Staff");
			//rootElement.appendChild(staff);

			// set attribute to staff element
			//Attr attr = doc.createAttribute("id");
			//attr.setValue("1");
			//staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// firstname elements
			Element nroTarjetaElement = doc.createElement("nroTarjeta");
			nroTarjetaElement.appendChild(doc.createTextNode(nroTarjeta));
			rootElement.appendChild(nroTarjetaElement);

			// lastname elements
			Element fechaVencimientoElement = doc.createElement("fechaVencimiento");
			fechaVencimientoElement.appendChild(doc.createTextNode(fechaVencimiento));
			rootElement.appendChild(fechaVencimientoElement);

			// nickname elements
			Element digitoVerificadorElement = doc.createElement("digitoVerificador");
			digitoVerificadorElement.appendChild(doc.createTextNode(digitoVerificador));
			rootElement.appendChild(digitoVerificadorElement);

			// salary elements
			Element montoElement = doc.createElement("monto");
			montoElement.appendChild(doc.createTextNode(monto));
			rootElement.appendChild(montoElement);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			//System.out.println(source.toString());
			return source;
			//StreamResult result = new StreamResult(new File("C:\\file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			//transformer.transform(source, result);

			//System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		return null;
		}
	
}
