package esb;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import esb.auth.Authenticator;
import esb.auth.InsecureHostnameVerifier;
import esb.auth.InsecureTrustManager;
import esb.dtos.Confirmacion;
import esb.dtos.Pago;

@WebService(endpointInterface = "esb", serviceName = "PagosYa")
public class PagosYaImp implements PagosYa {

	private final String BASE_PAGOSYA_URL = "https://localhost:8443/pagos-ya/servicios";
	private final String PAGOSYA_USERNAME = "admin";
	private final String PAGOSYA_PASSWORD = "admin";

	public long confirmarPago(String nroTarjeta, String fechaVencimiento, String digitoVerificador, String monto) {
		Logger logger = Logger.getAnonymousLogger();
		Client client = null;
		try {
			client = initClient(new ClientConfig().register(LoggingFilter.class))
					.register(new Authenticator(PAGOSYA_USERNAME, PAGOSYA_PASSWORD));
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		WebTarget webTarget = client.target(BASE_PAGOSYA_URL).path("confirmacionPago");
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
		Client client = null;
		try {
			client = initClient(new ClientConfig().register(LoggingFilter.class))
					.register(new Authenticator(PAGOSYA_USERNAME, PAGOSYA_PASSWORD));
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		WebTarget webTarget = client.target(BASE_PAGOSYA_URL).path("anulacionPago");
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

	public Client initClient(Configuration config) throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSL");
		TrustManager[] trustAllCerts = { new InsecureTrustManager() };
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
		Client client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();
		return client;
	}

}
