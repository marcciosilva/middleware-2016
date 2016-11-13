/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

/**
 *
 * @author javier
 */
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;
 
public class ServerPasswordCallback implements CallbackHandler {
 
    public void handle(Callback[] callbacks) throws IOException, 
        UnsupportedCallbackException {
 
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
 
        if (pc.getIdentifier().equals("joe")) {
            // set the password on the callback. This will be compared to the
            // password which was sent from the client.
            pc.setPassword("password");
        }
    }
 
}