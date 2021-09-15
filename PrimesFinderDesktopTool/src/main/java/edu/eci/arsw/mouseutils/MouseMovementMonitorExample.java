/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.mouseutils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class MouseMovementMonitorExample {
 
    public static void main(String a[]){
        
        //Este programa revisa 100 veces, cada segundo, cuanto tiempo ha pasado
        //desde la última vez que se movió el Mouse. Para probarlo, ejecútelo
        //deje quieto el mouse, y vea cómo el valor va aumentando. Una vez
        //mueva el mouse, dicho valor debe disminuir a uno cercano a cero.
        for (int i=0;i<100;i++){
            System.out.println("tiempo transcurrido desde el último movimiento de mouse:");
            System.out.println(MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MouseMovementMonitorExample.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
