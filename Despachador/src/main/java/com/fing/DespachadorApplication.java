package com.fing;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
public class DespachadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DespachadorApplication.class, args);


//		ApplicationContext context = new ClassPathXmlApplicationContext("src/main/resources/Bean.xml");



		// Simple Service


		/*EnviarMobileSys mobileSys = new EnviarMobileSys();
		mobileSys.EnviarMensaje("Primer mensaje despachador");
		mobileSys.EnviarMensaje("Segundo mensaje despachador");
		mobileSys.EnviarMensaje("Tercer mensaje despachador");
		mobileSys.EnviarMensaje("Cuarto mensaje despachador");*/

		try
		{

			String direccion = ActiveMQConnection.DEFAULT_BROKER_URL;

			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(direccion);
			RedeliveryPolicy redeliveryPolicy = connectionFactory.getRedeliveryPolicy();
			redeliveryPolicy.setMaximumRedeliveries(6);

			// Create a Connection
			Connection connection;
			connection = connectionFactory.createConnection();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the source (Topic or Queue)
			Destination source = session.createQueue("Generator-Despachador");
			
			// Creo destino para ordenes invalidas
			Destination invalidOrdersChannel = session.createQueue("Despachador-Invalidas");
			
			// Creo destino para ordenes validas
			Destination validOrdersChannel = session.createQueue("Despachador-Validas");

			EventDrivenConsumer consumidor1 = new EventDrivenConsumer("Consumidor 1", session,
					source, invalidOrdersChannel, validOrdersChannel);
			consumidor1.CrearConsumidor();
			connection.start();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
