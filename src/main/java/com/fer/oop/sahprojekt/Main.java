/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author mario
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeLater(() -> {
        
            
                
                try {
                  sahFrame frame= new sahFrame();
                    
                  //  test frame = new test();
                    frame.setVisible(true);
                frame.setBounds(100,100,700,700);
                frame.setTitle("Šah xdddd");
                
               
                
                
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
               
                
                
                //frame.pack();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        
        Game game1 = new Game(player1, player2);
        
       
        
    }
}
