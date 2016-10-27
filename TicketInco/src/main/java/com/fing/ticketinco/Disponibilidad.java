/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Cami
 */
public class Disponibilidad {    
    String sector;    
    double precio;    
    int cantidad;

    public Disponibilidad()
    {
        sector = "";
        precio = 0;
        cantidad = 0;
    }
    
    public Disponibilidad(String sector, double precio, int cantidad)
    {
        this.sector = sector;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public String getSector()
    {
        return this.sector;
    }
    
    public void setSector(String sector)
    {
        this.sector = sector;
    }
    
    public double getPrecio()
    {
        return this.precio;
    }
    
    public void setPrecio(double precio)
    {
        this.precio = precio;
    }
    
    public int getCantidad()
    {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }
}
