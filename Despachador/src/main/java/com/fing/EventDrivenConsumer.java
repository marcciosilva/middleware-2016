package com.fing;

import java.io.ByteArrayInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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

	private boolean loggingEnabled = false;
	private String consumidor;
	private Destination destination;
	private Session session;
	private enum CurrencyEnum {
	    Pesos(858), Dolares(840);
	    private final int id;
	    CurrencyEnum(int id) { this.id = id; }
	    public int getValue() { return id; }
	}

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

	public void process(String value) {
		debugMsg(consumidor + " " + value);
		// El ok es la respuesta del procesamiento del xml. Si da error se manda
		// a la cola.
		boolean ok = checkCurrency(value) && filterXmlOrder(value);
		if (ok) {
			debugMsg("ok");
		} else {
			debugMsg("nok");
		}
//		try {
//			if (!ok) {
//				session.recover();
//			}
//		} catch (JMSException e) {
//			log.error("Error processing message", e);
//		}

	}

	private boolean checkCurrency(String value) {
		try {
			//Construccion de xPath.
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(new ByteArrayInputStream(value.getBytes()));
			XPath xPath = XPathFactory.newInstance().newXPath();
			//Se obtiene moneda del xml.
			String expression = "/dtoOrder/currency";
			int currency = Integer.parseInt(xPath.compile(expression).evaluate(xmlDocument));
			//Si la moneda es en pesos o dolares, es valida.
			return (currency == CurrencyEnum.Pesos.getValue() 
					|| currency == CurrencyEnum.Dolares.getValue()); 
		} catch (Exception e) {
			return false;
		}
	}

	private boolean filterXmlOrder(String xml) {
		try {
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.CEILING);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
			XPath xPath = XPathFactory.newInstance().newXPath();
			// Obtengo precios de cada item.
			String expression = "/dtoOrder/itemsList/price";
			NodeList priceNodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
			// Total de orden en construccion para comparar.
			double totalAmount = 0.0;
			for (int i = 0; i < priceNodeList.getLength(); i++) {
				String priceString = priceNodeList.item(i).getFirstChild().getNodeValue();
				// Sumo el precio del item al total en construccion de la orden.
				totalAmount += Double.parseDouble(priceString);
			}
			expression = "/dtoOrder/totalAmount";
			double xmlTotalAmount = Double.parseDouble(xPath.compile(expression).evaluate(xmlDocument));
			// Comparo cantidades redondeadas hacia arriba.
			if (df.format(xmlTotalAmount).equals(df.format(totalAmount))) {
				debugMsg("Order total and item values match.");
				debugMsg("Total amount is : " + Double.toString(totalAmount));
				return true;
			} else {
				debugMsg("Order total and item values do not match.");
				debugMsg("Calculated total amount is : " + Double.toString(totalAmount));
				debugMsg("Order total amount in .xml is : " + Double.toString(xmlTotalAmount));
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void debugMsg(String msg) {
		if (loggingEnabled) {
			System.out.println(msg);
		}
	}

}
