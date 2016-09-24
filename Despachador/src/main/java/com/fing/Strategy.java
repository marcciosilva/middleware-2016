/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier
 */
public class Strategy {
    private static List<String> lista =new ArrayList(); 
    public boolean canRelease(List<String> listaXml) {
    //int sum = 0;
    for (String xmlorder: listaXml) {
      //sum += 1;
      lista.add(xmlorder);
    }
    
    System.out.println("Strategy " + lista.size());
    // aca tengo q poner los 1000
    Boolean b= false;
    if (lista.size() >= 100)
    {
     System.out.println("Strategy++++++++++++++++++++++++++++++++++++");
     b=true;
     lista =new ArrayList(); 
    }
    //return true;
    return b;
  }
    
  
}
