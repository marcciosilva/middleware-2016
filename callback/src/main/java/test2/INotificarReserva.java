package test2;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

@WebService(serviceName = "INotificarReservaService")
@Addressing
@MTOM
public interface INotificarReserva {
	@WebMethod	
	public void ConfirmarReservaResponse(@WebParam(name = "idConfirmacion") long idConfirmacion, @WebParam(name = "imagenesBinarias") ArrayList<ImagenEntrada> imagenesBinarias);
}
