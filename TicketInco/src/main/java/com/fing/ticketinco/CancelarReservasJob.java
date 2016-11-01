/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 *
 * @author Cami
 */
public class CancelarReservasJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    // Say Hello to the World and display the date/time
    System.out.println("Ejecutando cancelar reserva " + new Date());
}
}
