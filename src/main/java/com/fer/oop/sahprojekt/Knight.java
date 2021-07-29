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
public class Knight extends ChessPiece {
     private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedRook;

    public Knight(Point position, Color color) {
        super(position, color);
        possibleMoves = new LinkedList<>();

    }

    @Override
    public List<Integer> getPossibleMoves() {
        return checkMoves();
    }

    private List<Integer> checkMoves() {


        
        possibleMoves.clear();
        int pos = ChessPiece.pointToInt(this.getPosition());
        List<Integer> lista = List.of(pos -17, pos - 6, pos -10, pos +6, pos + 10, pos + 17, pos + 15, pos - 15);// -17, -6, -10, +6, +10, +17, +15, -15 
        List<Point> pointList = List.of(new Point(-1,-2), new Point(-1,2),new Point(1,-2), new Point( 1,2), new Point(-2,-1), new Point(-2,1), new Point(2,-1), new Point(2,+1));
        //(-1,-2)(-1,2)(1,-2)(1,+2)(-2,-1)(-2,+1)(2,-1)(2,+1)
        //knigt, gui, rošada, šah, mat, 
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
        possibleMoves.clear();
        for(Point p: pointList){
            if(!isBeyondBounds(this.getPosition(), p)){
                    
                if(sahFrame.getFieldList().get(ChessPiece.pointToInt(Point.addTwoPoints(p, this.getPosition()))).getComponents().length != 0){
                   if( ((ChessPiece)sahFrame.getFieldList().get(ChessPiece.pointToInt(Point.addTwoPoints(p, this.getPosition()))).getComponents()[0]).getColor() != this.getColor())
                      //  System.out.println("Adding move: "+p);
                       possibleMoves.add(ChessPiece.pointToInt(Point.addTwoPoints(p, this.getPosition())));
                }
                else{
                    possibleMoves.add(ChessPiece.pointToInt(Point.addTwoPoints(p, this.getPosition())));
                }
            }
        }

        return possibleMoves;
    }

//        
    private boolean isBeyondBounds(Point knightPoint, Point movePoint){
        
        if(knightPoint.getX()+ movePoint.getX() <=7 && knightPoint.getX() + movePoint.getX() >=0 &&
                knightPoint.getY()+ movePoint.getY() <=7 && knightPoint.getY() + movePoint.getY() >=0){
           
            return false;
        }
        
        return true;
    }
    
    
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
    }

    @Override
    public void mouseExited(MouseEvent e) { //ovo je komentar
    }
    
    
    
}
