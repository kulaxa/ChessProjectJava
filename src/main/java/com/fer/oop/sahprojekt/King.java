/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;

/**
 *
 * @author mario
 */
public class King extends ChessPiece {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */

    private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedRook;

    public King(Point position, Color color) {
        super(position, color);
        possibleMoves = new LinkedList<>();

    }

    @Override
    public List<Integer> getPossibleMoves() {
        return checkMoves();
    }

    private List<Integer> checkMoves() {
        // int position = ChessPiece.pointToInt(this.getPosition());
//        int upLeft = 0, upRight = 0, downLeft = 0, downRight = 0;
//
//        boolean switchSide = false;
//
//        possibleMoves.clear();
//        int position = ChessPiece.pointToInt(this.getPosition());
//        int counter = 1;
//        System.out.println("position " + position);
//        System.out.println("positon: " + this.getPosition());
//        System.out.println("oe:" + (this.getPosition().getY() - counter));
//        int left = 0, right = 0, up = 0, down = 0;
//        if (this.getPosition().getX() != 0 && sahFrame.getFieldList().get(this.getPosition().getX() - 1).getComponents().length == 0) {
//            left++;
//        }
//        if (this.getPosition().getX() != 7 && sahFrame.getFieldList().get(this.getPosition().getX() + 1).getComponents().length == 0) {
//            right++;
//        }
//        if (this.getPosition().getY() != 0 && sahFrame.getFieldList().get(this.getPosition().getX() - 8).getComponents().length == 0) {
//            up++;
//        }
//        if (this.getPosition().getY() != 7 && sahFrame.getFieldList().get(this.getPosition().getX() + 8).getComponents().length == 0) {
//            down++;
//        }
////        System.out.println("left: " + left);
////        System.out.println("right: " + right);
////        System.out.println("up: " + up);
////        System.out.println("down: " + down);
////        
//
//        for (int i = 1; i <= left; i++) {
//            int num = this.getPosition().getY() * 8 + this.getPosition().getX() - i;
//            possibleMoves.add(num);
//            //System.out.println("Rook move: "+ num);
//        }
//        for (int i = 1; i <= right; i++) {
//            int num = this.getPosition().getY() * 8 + this.getPosition().getX() + i;
//            possibleMoves.add(num);
//            //System.out.println("Rook move: "+ num);
//        }
//        for (int i = 1; i <= up; i++) {
//            int num = this.getPosition().getY() * 8 - 8 * i + this.getPosition().getX();
//            possibleMoves.add(num);
//            //System.out.println("Rook move: "+ num);
//        }
//        for (int i = 1; i <= down; i++) {
//            int num = this.getPosition().getY() * 8 + 8 * i + this.getPosition().getX();
//            possibleMoves.add(num);
//        }
//        //System.out.print("rook possible move: ");
//        possibleMoves.forEach(move -> System.out.print(" " + move));

        
        possibleMoves.clear();
        int pos = ChessPiece.pointToInt(this.getPosition());
        List<Integer> lista = List.of(pos + 1, pos - 1, pos + 8, pos - 8, pos - 7, pos + 7, pos + 9, pos - 9);

        for (Integer i : lista) {
            if (i >= 0 && i < 63) {

                if(sahFrame.getFieldList().get(i).getComponents().length != 0){
                   if( ((ChessPiece)sahFrame.getFieldList().get(i).getComponents()[0]).getColor() != this.getColor())
                       possibleMoves.add(i);
                }
                else{
                    possibleMoves.add(i);
                }
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
    public void mouseEntered(MouseEvent e) {
        //System.out.println("42");
    }

    @Override
    public void mouseExited(MouseEvent e) { //ovo je komentar
        //System.out.println("42");
    }
    
    
    
    //knight- (-17, -6, -10, +6, +10, +17, +15, -15 
}
