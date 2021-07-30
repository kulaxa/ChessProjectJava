/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author mario
 */
public class Bishop extends ChessPiece {

    private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedRook;

    public Bishop(Point position, Color color) {
        super(position, color);
        possibleMoves = new LinkedList<>();

    }

    @Override
    public List<Integer> getPossibleMoves() {
        return checkMoves();
    }

    private List<Integer> checkMoves() {
        // int position = ChessPiece.pointToInt(this.getPosition());
        int upLeft = 0, upRight = 0, downLeft = 0, downRight = 0;

        boolean switchSide = false;

        possibleMoves.clear();
        int position = ChessPiece.pointToInt(this.getPosition());
        int counter = 1;
        
        while (this.getPosition().getX() - counter >=0 && this.getPosition().getY() - counter >=0) { 
            
            if (sahFrame.getFieldList().get(position - counter * 9).getComponents().length == 0) {
                possibleMoves.add(position - counter * 9);

                counter++;

            }
            else{
                    if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getY() * 8 + this.getPosition().getX() - 9 * counter).getComponent(0)).getColor()) { 
                        possibleMoves.add(position - counter * 9);
                          
                            }                     
                break;
            }
            

        }
        counter =1;
        while (this.getPosition().getX() + counter <=7 && this.getPosition().getY() - counter >=0) {
            
           
            if (sahFrame.getFieldList().get(position - counter * 7).getComponents().length == 0) {
                
                possibleMoves.add(position - counter * 7);

                counter++;

            }
            else{
                            if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getY() * 8 + this.getPosition().getX() - 7 * counter).getComponent(0)).getColor()) { 
                                possibleMoves.add(position - counter * 7);
                          
                           }              
                break;
            }
        
        }
           counter =1;
        while (this.getPosition().getX() - counter >=0 && this.getPosition().getY() + counter <=7) {
            
            
            if (sahFrame.getFieldList().get(position + counter * 7).getComponents().length == 0) {
                
               possibleMoves.add(position + counter * 7);

                counter++;

            }
            else{
               if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getY() * 8 + this.getPosition().getX() + 7 * counter).getComponent(0)).getColor()) { 
                                possibleMoves.add(position + counter * 7);
                          
                            }
                break;
            }
        
        }
           counter =1;
        while (this.getPosition().getX() + counter <=7 && this.getPosition().getY() + counter <=7) {
            
            if (sahFrame.getFieldList().get(position + counter * 9).getComponents().length == 0) {
               possibleMoves.add(position + counter * 9);

                counter++;

            }
            else{
                if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getY() * 8 + this.getPosition().getX() + 9 * counter).getComponent(0)).getColor()) { 
                                possibleMoves.add(position + counter * 9);
                          
                            }
                break;
            }
        
        }
        
        
        return possibleMoves;
    }

//        
    



@Override
        public void mouseClicked(MouseEvent e) {

        ChessPiece paneltemp = (ChessPiece) e.getComponent();
        clickedRook = paneltemp;

    }

    @Override
        public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public void mouseEntered(MouseEvent e) {}

    @Override
        public void mouseExited(MouseEvent e) { //ovo je komentar
    }

}
