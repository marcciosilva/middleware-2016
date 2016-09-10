package com.fing;

import java.io.StringWriter;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fing.domain.DtoOrder;
import com.fing.util.OrderGenerator;

@SpringBootApplication
public class GeneratorSysApplication {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // default
																		// url
																		// of
																		// jms
																		// server
																		// -
																		// localhost
	private static String subject = "Generator-Despachador"; // name of the
																// queue on AMQ
																// server

	public static void enviarMensaje(String mensaje) {
		try {
			ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);

			Connection connection = activeMQConnectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject);

			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			for (int i = 0; i < 100; i++) {
				System.out.println();
				DtoOrder order = OrderGenerator.generateOrder();

				JAXBContext jaxbContext = JAXBContext.newInstance(DtoOrder.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				StringWriter writer = new StringWriter();

				jaxbMarshaller.marshal(order, writer);

				TextMessage message = session.createTextMessage(writer.toString());
				// Here we are sending the message!
				producer.send(message);
				System.out.println("Sent message '" + message.getText() + "'");
			}

			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		enviarMensaje("prueba");
	}

}
