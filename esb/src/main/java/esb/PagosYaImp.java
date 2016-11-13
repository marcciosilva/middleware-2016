package esb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

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
import com.google.gson.JsonObject;

import esb.dtos.Confirmacion;
import esb.dtos.Pago;

@WebService(endpointInterface = "esb", serviceName = "PagosYa")
public class PagosYaImp implements PagosYa {
	public long confirmarPago(String nroTarjeta, String fechaVencimiento, String digitoVerificador, String monto) {
		Logger logger = Logger.getAnonymousLogger();
		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target("http://localhost:8080/pagos-ya/servicios").path("confirmacionPago");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		// Construyo objeto pago a enviar.
		Pago pago = new Pago();
		logger.info("Fecha de vencimiento recibida: " + fechaVencimiento);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		try {
			pago.setFechaVencimiento(sdf.parse(fechaVencimiento));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pago.setDigitoVerificador(Integer.parseInt(digitoVerificador));
		pago.setMonto(Double.parseDouble(monto));
		pago.setNumeroTarjeta(Long.parseLong(nroTarjeta));
		logger.info("Pago recibido: " + pago.toString());
		// Hago request y espero respuesta.
		Response response = invocationBuilder.post(Entity.json(pago));
		String jsonString = response.readEntity(String.class);
		logger.info("Mensaje recibido: " + jsonString);
		// Parseo respuesta.
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
		Long idConfirmacion = Long.parseLong(jsonObject.get("idConfirmacionPago").toString());
		logger.info("id confirmacion recibido es: " + Long.toString(idConfirmacion));
		return idConfirmacion;
	}

	public long anularPago(String idConfirmacionPago) {
		Logger logger = Logger.getAnonymousLogger();
		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target("http://localhost:8080/pagos-ya/servicios").path("anulacionPago");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		// Construyo objeto confirmacion a enviar.
		Confirmacion confirmacion = new Confirmacion();
		confirmacion.setIdConfirmacionPago(Long.parseLong(idConfirmacionPago));
		// Recibo respuesta.
		Response response = invocationBuilder.put(Entity.json(confirmacion));
		String jsonString = response.readEntity(String.class);
		logger.info("Mensaje recibido: " + jsonString);
		// Parseo respuesta.
		JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
		JsonElement jsonElement = jsonObject.get("idConfirmacionAnulacionPago");
		Long idConfirmacionAnulacion = Long.parseLong(jsonElement.toString());
		return idConfirmacionAnulacion;
	}

}
