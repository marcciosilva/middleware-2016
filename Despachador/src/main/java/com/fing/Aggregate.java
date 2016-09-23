/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;
import com.fing.Domain.DtoOrder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.soap.*;
/**
 *
 * @author javier
 */

public class Aggregate {
  
    public Long AddXml(List<String> listaXml) {
      try {
           List<DtoOrder> listaordenes = new ArrayList();
           for (String xmlorder : listaXml) {

              JAXBContext jc = JAXBContext.newInstance(DtoOrder.class);
              Unmarshaller u = jc.createUnmarshaller();
              StringBuffer xmlStr = new StringBuffer( xmlorder );
              Object o = u.unmarshal( new StreamSource( new StringReader( xmlStr.toString() ) ) );
              DtoOrder order = (DtoOrder) o;               
              listaordenes.add(order);
           }
           //invocar web service            
           invokeService(listaordenes);
      }
      catch (Exception e) {
              System.out.println(e.getMessage());
      }

      long total = 0;
      System.out.println("LLego");
      return total;
    }
    
    
    public void invokeService(List<DtoOrder> listaOrdenes)
    {
       try{
         SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
         SOAPConnection soapConnection = soapConnectionFactory.createConnection();
         String soapRequestXml="<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" +
"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
"  <soap:Body>\n" +
"    <ProcesarItems xmlns=\"http://redstrawberry.fing.com.main/\">\n" +
"      <ListaItem xmlns=\"\">\n" +
"        <cantidad>2</cantidad>\n" +
"        <fechaHora>SADSA</fechaHora>\n" +
"        <identificadorproducto>0</identificadorproducto>\n" +
"        <identificadortransaccion>12332</identificadortransaccion>\n" +
"      </ListaItem>\n" +
"      <ListaItem xmlns=\"\">\n" +
"        <cantidad>33333</cantidad>\n" +
"        <fechaHora>SADSA</fechaHora>\n" +
"        <identificadorproducto>0</identificadorproducto>\n" +
"        <identificadortransaccion>12332</identificadortransaccion>\n" +
"      </ListaItem>\n" +
"    </ProcesarItems>\n" +
"  </soap:Body>\n" +
"</soap:Envelope>";
         TrustManager[] trustAllCerts = new TrustManager[] {
                         new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                            }

                            public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

                            public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

                            }
                        };
                        
                        SSLContext sc = SSLContext.getInstance("SSL");
                        sc.init(null, trustAllCerts, new java.security.SecureRandom());
                        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                        // Create all-trusting host name verifier
                        HostnameVerifier allHostsValid = new HostnameVerifier() {
                            public boolean verify(String hostname, SSLSession session) {
                              return true;
                            }
                        };
                        // Install the all-trusting host verifier
                        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
         
         
                         String authorization = new sun.misc.BASE64Encoder().encode(("12:12").getBytes());
  
         
         SOAPMessage soapRequest = MessageFactory.newInstance().createMessage(new MimeHeaders(),
            new ByteArrayInputStream(soapRequestXml.getBytes()));
                      MimeHeaders hd = soapRequest.getMimeHeaders();
            hd.addHeader("Authorization", "Basic " + authorization);
    // Send SOAP Message to SOAP Server
        SOAPMessage soapResponse = soapConnection.call(soapRequest, "https://localhost:8443/RedStrawberry/RedStrawberryWS?wsdl");

        ByteArrayOutputStream soapResponseBaos = new ByteArrayOutputStream();
        soapResponse.writeTo(soapResponseBaos);
        String soapResponseXml = soapResponseBaos.toString();

        
        
       }   
          catch (Exception e) {
              System.out.println(e.getMessage());
      }
    
    
    }
  
  }