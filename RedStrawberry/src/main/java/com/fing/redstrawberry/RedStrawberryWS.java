/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.redstrawberry;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author javier
 */
@WebService(serviceName = "RedStrawberryWS")
public class RedStrawberryWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ProcesarItems")
    public String ProcesarItems(@WebParam(name = "ListaItem") Item[] lista) {
        return "Hello " ;
    }
}
