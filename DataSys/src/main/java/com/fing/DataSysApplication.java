package com.fing;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSysApplication.class, args);
        try {
            String direccion = ActiveMQConnection.DEFAULT_BROKER_URL;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(direccion);
            RedeliveryPolicy redeliveryPolicy = connectionFactory.getRedeliveryPolicy();
            redeliveryPolicy.setMaximumRedeliveries(6);
            Connection connection;
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Despachador-DataSys");
            EventDrivenConsumer consumidor = new EventDrivenConsumer("Consumidor", session, destination);
            consumidor.CrearConsumidor();
            connection.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
