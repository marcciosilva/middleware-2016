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

@SpringBootApplication
public class DespachadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DespachadorApplication.class, args);

		EnviarMobileSys mobileSys = new EnviarMobileSys();
		mobileSys.EnviarMensaje("Primer mensaje despachador");
		mobileSys.EnviarMensaje("Segundo mensaje despachador");
		mobileSys.EnviarMensaje("Tercer mensaje despachador");
		mobileSys.EnviarMensaje("Cuarto mensaje despachador");
		testDataSysInsert();

		try {
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

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("Generator-Despachador");
			
			// Creo destino para ordenes invalidas
			Destination invalidOrdersChannel = session.createQueue("Despachador-Invalidas");

			EventDrivenConsumer consumidor1 = new EventDrivenConsumer("Consumidor 1", session,
					destination, invalidOrdersChannel);
			consumidor1.CrearConsumidor();
			connection.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void testDataSysInsert() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out
					.println("Driver de JDBC para PostgreSQL no incluído; incluir en library path del proyecto.");
			e.printStackTrace();
			return;
		}
		System.out.println("Driver de JDBC para PostgreSQL registrado.");
		java.sql.Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/DataSys",
					"postgres", "1234");
		} catch (SQLException e) {
			System.out.println("Conexión fallida.");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Conexion con base exitosa.");
			System.out.println("Insert random...");
			try {
				Statement statement = connection.createStatement();
				statement
						.executeUpdate("insert into orders(clientid, itemid, orderid, productid, quantity) "
								+ "values(394, 3012, 11232, 2812311, 18122)");
				System.out.println("Insert realizado.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Conexion fallida.");
		}
	}

}
