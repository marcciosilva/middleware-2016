/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing;

import java.util.List;

/**
 *
 * @author javier
 */
public class Strategy {
    public boolean canRelease(List<String> listaXml) {
    int sum = 0;
    for (String xmlorder: listaXml) {
      sum += 1;
    }
    // aca tengo q poner los 1000
    return sum >= 2;
  }
}
