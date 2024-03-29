package com.fing;

import org.springframework.jms.core.JmsTemplate;

public class EnviarColaLocal {
	
	private JmsTemplate jmsTemplate;
	
	public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
	
	public void EnviarCola(String mensaje, String destino){
		/*System.out.println("Mensaje a transformar" + mensaje);*/
		jmsTemplate.convertAndSend(destino, mensaje);
		
	}
	
	public void processEmployee(String mensaje) {
        System.out.println("Mensaje: " + mensaje + " processed");
    }
}
