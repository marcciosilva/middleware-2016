package com.fing;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class EnviarMobileSys {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;	//default url of jms server - localhost
    private static String subject = "Despachador-Mobile";	//name of the queue on AMQ server
    
    public void EnviarMensaje(String mensaje)
    {
    	try
    	{
    		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start(); 
            
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            Destination destination = session.createQueue(subject);
            
            MessageProducer producer = session.createProducer(destination);
            
            TextMessage message = session.createTextMessage(mensaje);

            // Here we are sending the message!
            producer.send(message);
            System.out.println("Sent message '" + message.getText() + "'");

            connection.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }

}
