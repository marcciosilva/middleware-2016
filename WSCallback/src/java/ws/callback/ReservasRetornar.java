/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.callback;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Cami
 */
public class ReservasRetornar {
    @XmlElement
    byte[] imagenEntrada;
    @XmlElement
    long idReserva;
}
