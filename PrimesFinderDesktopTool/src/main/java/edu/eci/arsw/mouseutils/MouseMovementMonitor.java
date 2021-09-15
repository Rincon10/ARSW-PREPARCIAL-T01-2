/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.mouseutils;

/**
 *
 * @author hcadavid
 */
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MouseMovementMonitor {

    private static MouseMovementMonitor instance=new MouseMovementMonitor();
    
    long lastMovementTime=System.currentTimeMillis();
    
    public static MouseMovementMonitor getInstance(){
        return instance;
    }
    
    Robot robot;    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

    private MouseMovementMonitor() {
        try {
            
            robot = new Robot();
            
            ActionListener al = new ActionListener() {
                
                Point lastPoint;
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    if (!p.equals(lastPoint)) {
                        lastMovementTime=System.currentTimeMillis();
                    }
                    lastPoint = p;
                }
            };
            Timer timer = new Timer(40, al);
            timer.start();
            
        } catch (AWTException ex) {
            Logger.getLogger(MouseMovementMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public long getTimeSinceLastMouseMovement(){
        return System.currentTimeMillis()-lastMovementTime;
    }



}
