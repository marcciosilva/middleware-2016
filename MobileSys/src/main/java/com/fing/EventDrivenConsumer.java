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
			// TODO Auto-generated catch block
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



}
