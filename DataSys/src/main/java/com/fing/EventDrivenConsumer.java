package com.fing;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.handler.MessageProcessor;



public class EventDrivenConsumer implements MessageListener {

	private String consumidor;
	private Destination destination;
	private Session session;

	EventDrivenConsumer(String consumidor, Session session, Destination destination)
	{
		this.consumidor = consumidor;
		this.destination = destination;
		this.session = session;
	}	

	public void CrearConsumidor() {
		try {							
			MessageConsumer consumer = session.createConsumer(destination);	
			consumer.setMessageListener(this);
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
	}

	private Log log = LogFactory.getLog(MessageProcessor.class);

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage)message;
		try {
			String value = msg.getText();	      
			process(value);			
		}
		catch(JMSException e) {
			log.error("Error processing message", e);
			
		}
	}

	public void process(String value) {
		System.out.println(consumidor + " " + value);
		//El ok es la respuesta del procesamiento del xml. Si da error se manda a la cola.
		boolean ok = true;
		try
		{
			if(!ok)
			{
				session.recover();
			}			
		}
		catch(JMSException e)
		{
			log.error("Error processing message", e);
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
