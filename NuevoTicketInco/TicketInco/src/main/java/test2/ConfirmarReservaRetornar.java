package test2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class ConfirmarReservaRetornar {
	@XmlElement
	long idConfirmacion;
	@XmlElement
	ArrayList<ImagenEntrada> imagenesBinarias; 
	
	public ConfirmarReservaRetornar()
	{
		idConfirmacion = -1;
		imagenesBinarias = new ArrayList<ImagenEntrada>();
	}
}
