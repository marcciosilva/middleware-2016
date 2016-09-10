package com.fing;

import java.io.ByteArrayInputStream;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.handler.MessageProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class EventDrivenConsumer implements MessageListener {

	private String consumidor;
	private Destination destination;
	private Session session;
	private java.sql.Connection connection = null;
	private String url = "jdbc:postgresql://127.0.0.1:5432/DataSys";
	private String username = "postgres";
	private String pass = "1234";

	EventDrivenConsumer(String consumidor, Session session, Destination destination) {
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
		TextMessage msg = (TextMessage) message;
		try {
			String value = msg.getText();
			process(value);
		} catch (JMSException e) {
			log.error("Error processing message", e);

		}
	}

	public void process(String xml) {
		System.out.println(consumidor + " " + xml);
		if (connection == null) {
			connectToDatabase(url, username, pass);
			System.out.println("Conexion con base establecida.");
			// Proceso de nuevo, si no se pierde el mensaje.
			process(xml);
		} else {
			try {
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = null;
				builder = builderFactory.newDocumentBuilder();
				Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
				XPath xPath = XPathFactory.newInstance().newXPath();
				// Obtengo identificador del cliente.
				String expression = "/dtoOrder/customerId";
				int clientId = Integer.parseInt(xPath.compile(expression).evaluate(xmlDocument));
				// Obtengo identificador de la orden.
				expression = "/dtoOrder/orderNumber";
				int orderId = Integer.parseInt(xPath.compile(expression).evaluate(xmlDocument));
				// Obtengo lista de identificadores de producto de los items.
				expression = "/dtoOrder/itemsList/productId";
				NodeList productIdNodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument,
						XPathConstants.NODESET);
				// Obtengo lista de cantidades asociadas a los items.
				expression = "/dtoOrder/itemsList/amount";
				NodeList quantityNodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument,
						XPathConstants.NODESET);
				// Obtengo lista de identificadores de items.
				expression = "/dtoOrder/itemsList/itemNumber";
				NodeList itemIdNodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument,
						XPathConstants.NODESET);
				for (int i = 0; i < productIdNodeList.getLength(); i++) {
					int quantity = Integer.parseInt(quantityNodeList.item(i).getFirstChild().getNodeValue());
					int productId = Integer.parseInt(productIdNodeList.item(i).getFirstChild().getNodeValue());
					int itemId = Integer.parseInt(itemIdNodeList.item(i).getFirstChild().getNodeValue());
					// Inserto tupla en base.
					Statement statement = connection.createStatement();
					statement.executeUpdate("insert into orders(clientid, itemid, orderid, productid, quantity) "
							+ "values(" + Integer.toString(clientId) + ", " + Integer.toString(itemId) + ", "
							+ Integer.toString(orderId) + ", " + Integer.toString(productId) + ", "
							+ Integer.toString(quantity) + ")");
					System.out.println("Insert realizado.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void connectToDatabase(String url, String username, String pass) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver de JDBC para PostgreSQL no incluído; incluir en library path del proyecto.");
			e.printStackTrace();
			return;
		}
		System.out.println("Driver de JDBC para PostgreSQL registrado.");
		connection = null;
		try {
			connection = DriverManager.getConnection(url, "postgres", "1234");
		} catch (SQLException e) {
			System.out.println("Conexión fallida.");
			e.printStackTrace();
			return;
		}
	}

}
