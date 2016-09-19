package com.fing;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.handler.MessageProcessor;

public class EventDrivenCanalValido implements MessageListener {
	
	private Destination destination;
	private Session session;
	private Log log = LogFactory.getLog(MessageProcessor.class);
	
	public EventDrivenCanalValido(Destination destino, Session sesion)
	{
		this.destination = destino;
		this.session = sesion;
	}

	public void CrearConsumidor() {
		try {
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			String value = msg.getText();
			System.out.println("mensaje del canal valido " + value);
			ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");				
			EnviarColaLocal endpoint = (EnviarColaLocal) context.getBean("entrada", EnviarColaLocal.class);
			endpoint.EnviarCola(value, "ColaTranslator1");
			
		} catch (JMSException e) {
			log.error("Error processing message", e);

		}		
	}

}
