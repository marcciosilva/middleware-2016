package com.fing;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class EventDrivenConsumer implements MessageListener{
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

	//private Log log = LogFactory.getLog(MessageProcessor.class);

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage)message;
		try {
			String value = msg.getText();	      
			process(value);			
		}
		catch(JMSException e) {
			//log.error("Error processing message", e);
			try
			{
				session.recover();
			}
			catch(Exception ex)
			{
				//log.error("Error processing message", e);
			}
		}
		catch(Exception e)
		{
			//log.error("Error processing message", e);
		}
	}

	public void process(String value) {
		System.out.println(value);		
		
	}
}
