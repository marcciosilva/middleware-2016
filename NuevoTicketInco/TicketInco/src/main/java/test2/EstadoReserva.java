/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

/**
 *
 * @author javier
 */
public class EstadoReserva {
    int estado;// o cancelado, 1 pendiente, 2 confirmado
    String Errores;
    
    public EstadoReserva()
    {
        estado= 1;
        Errores = "";        
    }
    
     public int getestado()
    {
        return estado;
    }
    
    public void setestado(int estado)
    {
        this.estado = estado;
    }
    
    public String getErrores()
    {
        return Errores;
    }
    
    public void setErrores(String Errores)
    {
        this.Errores = Errores;
    }
}
