/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mario
 */
public class test extends JFrame {
    
    
    public test() throws IOException{
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    
    
    
    label.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
          
       
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            label.setText("42");
        }

        @Override
        public void mouseExited(MouseEvent e) {
           label.setText("ne 42");
        }
    });
    panel.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("ajsdfjasdfjasdfjasdjfas");
        }

        @Override
        public void mouseExited(MouseEvent e) {
System.out.println("ajsdfjasdfjasdfjasdjfas");        }
    });
    
    
    
   // panel.setBackground(java.awt.Color.BLUE);
        label.setText("masdmf");
        label.setForeground(java.awt.Color.red);
        
   // panel.add(label);
     File file = new File("whtepawn - Copy.png");
        BufferedImage image;
            try {
                image = ImageIO.read(file);
                 ImageIcon icon  = new ImageIcon(image);
       Pawn pawn1 = new Pawn( new Point(0,0), com.fer.oop.sahprojekt.Color.WHITE);
        //ChessPiece piece = (ChessPiece)pawn1;
       //ChessPiece pawn1 = new ChessPiece(new Point(0,0), com.fer.oop.sahprojekt.Color.WHITE);
        //label.setIcon(icon);
        pawn1.setText("mar");
        pawn1.setIcon(icon);
        
        panel.add(label);
        panel.add(pawn1);
            } catch (IOException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    add(panel);

      
    }
    
    
}
