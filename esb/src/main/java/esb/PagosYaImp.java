package esb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@WebService(endpointInterface = "esb", serviceName = "PagosYa")
public class PagosYaImp implements PagosYa {
	public long confirmarPago(String nroTarjeta, String fechaVencimiento, String digitoVerificador, String monto) {
		Logger logger = Logger.getAnonymousLogger();
		String json = null;
		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target("http://172.16.149.38:8084/pagos-ya/servicios").path("pagoEjemplo");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		json = response.readEntity(String.class);
		logger.info("Mensaje asda");
		logger.info("Mensaje recibido: " + json);

		Gson gson = new Gson();
		esb.Pago pago = gson.fromJson(json, Pago.class);
		logger.info("Pago creado: " + pago.toString());
		logger.info("asdasdjasd");

		// // 1. Java object to JSON, and save into a file
		//
		// // 2. Java object to JSON, and assign to a String
		// String jsonInString = gson.toJson(obj);

		// Client client = ClientBuilder.newClient();
		// WebTarget target =
		// client.target("http://172.16.149.38:8084/pagos-ya/servicios/pagoEjemplo");
		// // Genero un objeto con los datos que precisa PagosYa!
		// // Genero objeto Calendar en base al string de fecha recibido.
		// Calendar calendar = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
		// Locale.ENGLISH);
		// calendar.setTime(sdf.parse(fechaVencimiento));
		// Pago pagoPagosYa = new Pago(Long.parseLong(nroTarjeta), calendar,
		// Integer.parseInt(digitoVerificador),
		// Double.parseDouble(monto));
		// // Genero una request y obtengo su response.
		// javax.ws.rs.core.Response response =
		// target.request(MediaType.APPLICATION_JSON)
		// .get();
		// msg = response.readEntity(Pago.class);
		// client.close();
		//
		// if (msg == null) {
		// System.out.println("El mensaje era nulo");
		// } else {
		// System.out.println("Mensaje recibido: " + msg);
		// }

		//
		// nroTarjeta = cambiarFormatoNroTarjeta(nroTarjeta);
		//
		// try {
		// fechaVencimiento = cambiarFormatoFechaVencimiento(fechaVencimiento);
		// } catch (ParseException e2) {
		// e2.printStackTrace();
		// }
		// digitoVerificador =
		// cambiarFormatoDigitoVerificador(digitoVerificador);
		// monto = cambiarFormatoMonto(monto);
		//
		// //Genero el xml a enviar
		// GenerarXML generarXML = new GenerarXML();
		// DOMSource xml = generarXML.GenerarXMLEnviar(nroTarjeta,
		// fechaVencimiento, digitoVerificador, monto);
		//
		// StringWriter sw = new StringWriter();
		// TransformerFactory tf = TransformerFactory.newInstance();
		// Transformer transformer;
		// try {
		// transformer = tf.newTransformer();
		//
		// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		// transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//
		// try {
		// transformer.transform(xml, new StreamResult(sw));
		// } catch (TransformerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (TransformerConfigurationException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// //return sw.toString();
		// EnviarMensajeACola enviarMensajeCola = new EnviarMensajeACola();
		// enviarMensajeCola.EnviarMensaje(sw.toString());
		// System.out.println(sw.toString());
		if (json == null)
			return 0;
		else
			return 13;
	}

	public long anularPago(String idConfirmacionPago) {
		// GenerarXML generarXML = new GenerarXML();
		// DOMSource xml = generarXML.GenerarXMLAnular(idConfirmacionPago);
		//
		// StringWriter sw = new StringWriter();
		// TransformerFactory tf = TransformerFactory.newInstance();
		// Transformer transformer;
		// try {
		// transformer = tf.newTransformer();
		//
		// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		// transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//
		// try {
		// transformer.transform(xml, new StreamResult(sw));
		// } catch (TransformerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (TransformerConfigurationException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// //return sw.toString();
		// EnviarMensajeACola enviarMensajeCola = new EnviarMensajeACola();
		// enviarMensajeCola.EnviarMensaje(sw.toString());
		// System.out.println(sw.toString());
		return 13;
	}

	private String cambiarFormatoNroTarjeta(String nroTarjeta) {
		while (nroTarjeta.length() < 16) {
			nroTarjeta = "0" + nroTarjeta;
		}
		if (nroTarjeta.length() > 16) {
			nroTarjeta = nroTarjeta.substring(0, 15);
		}

		return nroTarjeta;
	}

	private String cambiarFormatoDigitoVerificador(String digitoVerificador) {
		while (digitoVerificador.length() < 3) {
			digitoVerificador = "0" + digitoVerificador;
		}
		if (digitoVerificador.length() > 3) {
			digitoVerificador = digitoVerificador.substring(0, 2);
		}
		return digitoVerificador;
	}

	private String cambiarFormatoMonto(String monto) {
		String[] montoSplit = monto.split("\\.");
		String parteEntera = montoSplit[0];
		while (parteEntera.length() < 4) {
			parteEntera = "0" + parteEntera;
		}
		if (parteEntera.length() > 4) {
			parteEntera = parteEntera.substring(0, 3);
		}
		String parteDecimal = "00";
		if (montoSplit.length > 1) {
			parteDecimal = montoSplit[1];
			while (parteDecimal.length() < 2) {
				parteDecimal = parteDecimal + "0";
			}
			if (parteDecimal.length() > 2) {
				parteDecimal = parteDecimal.substring(0, 1);
			}
		}

		monto = parteEntera + "." + parteDecimal;
		return monto;
	}

	private String cambiarFormatoFechaVencimiento(String fechaVencimiento) throws ParseException {
		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy
		// hh:mm");
		// try {

		// Date date = formatter.parse(fechaVencimiento);
		// System.out.println(date);
		// System.out.println(formatter.format(date));
		// fechaVencimiento = formatter.format(date);

		// } catch (Exception e) {
		// e.printStackTrace();
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
