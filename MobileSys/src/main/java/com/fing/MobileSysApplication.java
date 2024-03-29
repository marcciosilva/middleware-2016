package com.fing;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.region.policy.RedeliveryPolicyMap;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.filter.DestinationMapEntry;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileSysApplication{

	public static void main(String[] args) {
		
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
	        
	        // Create the destination (Topic or Queue)
	        Destination destination = session.createQueue("Despachador-Mobile");
	        
	        EventDrivenConsumer consumidor1 = new EventDrivenConsumer("Consumidor 1", session, destination);
	        EventDrivenConsumer consumidor2 = new EventDrivenConsumer("Consumidor 2", session, destination);
	        consumidor1.CrearConsumidor();
	        consumidor2.CrearConsumidor();
	        connection.start();	        
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }        
		
}

	
	

