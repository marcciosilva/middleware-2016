package com.fing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DespachadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DespachadorApplication.class, args);
		
		EnviarMobileSys mobileSys = new EnviarMobileSys();
		mobileSys.EnviarMensaje("Primer mensaje despachador");
		mobileSys.EnviarMensaje("Segundo mensaje despachador");
		mobileSys.EnviarMensaje("Tercer mensaje despachador");
		mobileSys.EnviarMensaje("Cuarto mensaje despachador");
	}
}
