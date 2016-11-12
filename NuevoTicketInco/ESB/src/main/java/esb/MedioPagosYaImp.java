package esb;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@WebService(endpointInterface = "esb",
            serviceName = "MedioPagosYa")
public class MedioPagosYaImp implements MedioPagoLocal{
	public void confirmarPago(String nroTarjeta, String fechaVencimiento, String digitoVerificador, String monto){		
		
		nroTarjeta = cambiarFormatoNroTarjeta(nroTarjeta);
		
		try {
			fechaVencimiento = cambiarFormatoFechaVencimiento(fechaVencimiento);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		digitoVerificador = cambiarFormatoDigitoVerificador(digitoVerificador);		
		monto = cambiarFormatoMonto(monto);
		
		//Genero el xml a enviar
		GenerarXML generarXML = new GenerarXML();
		DOMSource xml = generarXML.GenerarXMLEnviar(nroTarjeta, fechaVencimiento, digitoVerificador, monto);
		
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
		EnviarMensajeACola enviarMensajeCola = new EnviarMensajeACola();
		enviarMensajeCola.EnviarMensaje(sw.toString());
		System.out.println(sw.toString());
	}
	
	public void anularPago(String idConfirmacionPago)
	{
		GenerarXML generarXML = new GenerarXML();
		DOMSource xml = generarXML.GenerarXMLAnular(idConfirmacionPago);
		
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
		EnviarMensajeACola enviarMensajeCola = new EnviarMensajeACola();
		enviarMensajeCola.EnviarMensaje(sw.toString());
		System.out.println(sw.toString());
	}
	
	private String cambiarFormatoNroTarjeta(String nroTarjeta)
	{
		while(nroTarjeta.length() < 16)
		{
			nroTarjeta = "0" + nroTarjeta;
		}
		if(nroTarjeta.length() > 16)
		{
			nroTarjeta = nroTarjeta.substring(0, 15);
		}
		
		return nroTarjeta;
	}
	
	private String cambiarFormatoDigitoVerificador(String digitoVerificador)
	{
		while(digitoVerificador.length() < 3)
		{
			digitoVerificador = "0" + digitoVerificador;
		}
		if(digitoVerificador.length() > 3)
		{
			digitoVerificador = digitoVerificador.substring(0, 2);
		}
		return digitoVerificador;
	}
	
	private String cambiarFormatoMonto(String monto)
	{
		String[] montoSplit = monto.split("\\.");
		String parteEntera = montoSplit[0];
		while(parteEntera.length() < 4)
		{
			parteEntera = "0" + parteEntera;
		}
		if(parteEntera.length() > 4)
		{
			parteEntera = parteEntera.substring(0, 3);
		}
		String parteDecimal = "00";
		if(montoSplit.length > 1)
		{
			parteDecimal = montoSplit[1];
			while(parteDecimal.length() < 2)
			{
				parteDecimal = parteDecimal + "0";
			}
			if(parteDecimal.length() > 2)
			{
				parteDecimal = parteDecimal.substring(0,1);
			}
		}
		
		monto = parteEntera + "." + parteDecimal;
		return monto;
	}
	
	private String cambiarFormatoFechaVencimiento(String fechaVencimiento) throws ParseException
	{
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		//try {

          //  Date date = formatter.parse(fechaVencimiento);
           // System.out.println(date);
            //System.out.println(formatter.format(date));
            //fechaVencimiento = formatter.format(date);

        //} catch (Exception e) {
          //  e.printStackTrace();
       // }
		String[] fechaVencimientoArray = fechaVencimiento.split(" ");
		String mes = fechaVencimientoArray[1];
		Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(mes);
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int month = cal.get(Calendar.MONTH) + 1;
		String dia = fechaVencimientoArray[2];
		String anio = fechaVencimientoArray[5];
		String horario = fechaVencimientoArray[3];
		String[] horarioArray = horario.split(":");
		String hora = horarioArray[0];
		String minutos = horarioArray[1];
		
		fechaVencimiento = dia + "-" + month + "-" + anio + " ";
		fechaVencimiento = fechaVencimiento + hora + ":" + minutos;
		return fechaVencimiento;
	}
}
