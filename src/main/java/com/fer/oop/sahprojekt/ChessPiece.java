/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;

/**
 *
 * @author mario
 */
public abstract class ChessPiece extends JLabel implements MouseListener{
    private Point position;
    private final Color color;
    static List<ChessPiece> pieces= new ArrayList<>();
    //static List<ChessPiece> piecesWhite= new ArrayList<>();
    

    public ChessPiece(Point position, Color color) {
        this.position = position;
        this.color = color;
        
        pieces.add(this);
        
    }
   
    public Color getColor() {
        return color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public static List<ChessPiece> getPieces() {
        return pieces;
    }
    
    public boolean checkIfEaten(){
        
        for(ChessPiece ch: sahFrame.getPieceList()){
            //if((ch.getPosition().getX() = this.getPosition().getX()) && (ch.getPosition().getY() = this.getPosition().getX()))
            if(ch.getPosition().equals(this.getPosition()) && ch != this)
                 return true;
            
        }
        
        return false;
    }
    
    
    public List<Integer> getLegalMoves(ChessPiece piece){
        
        List<Integer> listOfMoves = piece.getPossibleMoves();
        
        
        return listOfMoves;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
   
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public abstract List<Integer> getPossibleMoves();
    
    
    public static int pointToInt(Point point){
        
        if(point.getX() >7 || point.getX() <0)
            throw new IllegalArgumentException();
        if(point.getY() >7 || point.getY() < 0)
            throw  new IllegalArgumentException();
        return point.getY() *8 + point.getX();
        
    }
    
    public static Point intToPoint(int num){
        if(num > 63 || num < 0)
            throw new IllegalArgumentException();
        return new Point(num%8, num/8);
        
    }
    
    
    public static boolean checkForChess(){
        Color currentColor = Game.getCurrentColor();
         boolean result = false;
        King kingInChess=(King)
        sahFrame.getPieceList().stream().filter(pic -> pic instanceof King && pic.getColor() != currentColor).findAny().get();
//        long num=
//        sahFrame.getPieceList().stream().filter(pic -> pic.getColor() != currentColor).filter(pic -> 
//            
//        pic.getPossibleMoves().stream().filter(move -> move== ChessPiece.pointToInt(kingInChess.getPosition())).count() != 0
//           
//            
//            
//            
//        ).count();
//        if(num != 0){
//            result = true;
//        }


        List<ChessPiece>tempList=sahFrame.getPieceList().stream().filter(pic -> pic.getColor() != currentColor).collect(Collectors.toList());
        for(ChessPiece piece: tempList){
            for(Integer in: piece.getPossibleMoves()){
                System.out.println("this is pringing because iw t workings");
//                if(in ==ChessPiece.pointToInt(kingInChess.getPosition()))
//                    result= true;
            }
            
        }
        
        return result;
    }
    
}
