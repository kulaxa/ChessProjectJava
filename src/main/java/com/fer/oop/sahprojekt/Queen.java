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
public class Queen extends ChessPiece{
    private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedRook;

    public Queen(Point position, Color color) {
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
        
        int left = 0, right = 0, up = 0, down = 0;

        
        
       

        for (int i = 0; i < 2; i++) { //0 - x, 1 -y
            switchSide = false;
            for (int j = 0; j < 8; j++) {
                if (i == 0) {

                    if (this.getPosition().getX() == j) {
                        switchSide = true;

                    } else if (switchSide == false) {

                        if (sahFrame.getFieldList().get(this.getPosition().getY() * 8 + j).getComponents().length == 0) {
                            left++;
                        } else {
                            left = 0;

                            if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getY() * 8 + j).getComponent(0)).getColor()) {
                                left++;
                            }

                        }

                    } else {

                        if (sahFrame.getFieldList().get(this.getPosition().getY() * 8 + j).getComponents().length == 0) {
                            right++;
                        } else {

                            if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getY() * 8 + j).getComponent(0)).getColor()) {
                                right++;

                            }
                            break;
                        }

                    }

                } else {
                    // switchSide = false;

                    if (this.getPosition().getY() == j) {
                        switchSide = true;

                    } else if (switchSide == false) {

                        if (sahFrame.getFieldList().get(this.getPosition().getX() + j * 8).getComponents().length == 0) {
                            up++;

                        } else {
                            up = 0;

                            if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getX() + j * 8).getComponent(0)).getColor()) {
                                up++;
                            }

                        }

                    } else {

                        if (sahFrame.getFieldList().get(this.getPosition().getX() + j * 8).getComponents().length == 0) {
                            down++;
                        } else {
                            if (this.getColor() != ((ChessPiece) sahFrame.getFieldList().get(this.getPosition().getX() + j * 8).getComponent(0)).getColor()) {
                                down++;
                            }
                            break;
                        }

                    }

                }

            }
        }


        for (int i = 1; i <= left; i++) {
            int num = this.getPosition().getY() * 8 + this.getPosition().getX() - i;
            possibleMoves.add(num);
        }
        for (int i = 1; i <= right; i++) {
            int num = this.getPosition().getY() * 8 + this.getPosition().getX() + i;
            possibleMoves.add(num);
        }
        for (int i = 1; i <= up; i++) {
            int num = this.getPosition().getY() * 8 - 8 * i + this.getPosition().getX();
            possibleMoves.add(num);
        }
        for (int i = 1; i <= down; i++) {
            int num = this.getPosition().getY() * 8 + 8 * i + this.getPosition().getX();
            possibleMoves.add(num);
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
        public void mouseEntered(MouseEvent e) {
        //System.out.println("42");
    }

    @Override
        public void mouseExited(MouseEvent e) { //ovo je komentar
        //System.out.println("42");
    }
}
