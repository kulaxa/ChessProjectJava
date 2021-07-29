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
 *
 *
 * moves- gore1, gore2, jedno naprijed koso, ako jede, nemre ravno ako je netko
 * drugi naprijed
 */
public class Pawn extends ChessPiece {

    private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedPawn;
    private boolean alreadyMoved;

    public ChessPiece getClickedPawn() {
        return clickedPawn;
    }

    public Pawn(Point position, Color color) {
        super(position, color);

        alreadyMoved = false;
        possibleMoves = new LinkedList<>();

    }

    @Override
    public List<Integer> getPossibleMoves() {
        return checkMoves();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        ChessPiece paneltemp = (ChessPiece) e.getComponent(); //može provjera jeli japenl
        //ChessPiece piecetemp = (ChessPiece)paneltemp.getComponents()[0];
        //paneltemp.setPosition(new Point(0,0));
        //System.out.println(paneltemp.getPosition());
        clickedPawn = paneltemp;

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

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    private List<Integer> checkMoves() {

        possibleMoves.clear();
        if (this.getColor() == Color.WHITE) {

        
            possibleMoves.add(ChessPiece.pointToInt(this.getPosition()) + 8);
            if (!alreadyMoved) {
                //alreadyMoved = true;
                 
                possibleMoves.add(ChessPiece.pointToInt(this.getPosition()) + 16);
            }
            if (!possibleMoves.isEmpty()) {

            }
            if (!possibleMoves.isEmpty()) {
                for (Integer i : possibleMoves) {

                    if (checkForOtherPieces(sahFrame.getPieceList(), i)) {
                        
                        possibleMoves.remove(i);
                    }

                }
            }

            List<ChessPiece> pieceList = sahFrame.getPieceList();
            int currentPosition = ChessPiece.pointToInt(this.getPosition());
            List<ChessPiece> tempList
                    = pieceList.stream().filter(piece -> ChessPiece.pointToInt(piece.getPosition()) == (currentPosition + 7) || ChessPiece.pointToInt(piece.getPosition()) == (currentPosition + 9)).collect(Collectors.toList());
            tempList.stream().forEach(piece -> {
                if (this.getColor() != piece.getColor()) {
                    possibleMoves.add(ChessPiece.pointToInt(piece.getPosition()));
                }
            }
            );
        } else {

            possibleMoves.add(ChessPiece.pointToInt(this.getPosition()) - 8);
            if (!alreadyMoved) {
                //  alreadyMoved = true;

                possibleMoves.add(ChessPiece.pointToInt(this.getPosition()) - 16);
            }

            if (!possibleMoves.isEmpty()) {
                for (Integer i : possibleMoves) {

                    if (checkForOtherPieces(sahFrame.getPieceList(), i)) {
                        
                        possibleMoves.remove(i);
                    }

                }
            }
            List<ChessPiece> pieceList = sahFrame.getPieceList();
            int currentPosition = ChessPiece.pointToInt(this.getPosition());
            List<ChessPiece> tempList
                    = pieceList.stream().filter(piece -> ChessPiece.pointToInt(piece.getPosition()) == (currentPosition - 7)
                            || ChessPiece.pointToInt(piece.getPosition()) == (currentPosition - 9)).collect(Collectors.toList());
            tempList.stream().forEach(piece -> {
                if (this.getColor() != piece.getColor()) {
                    possibleMoves.add(ChessPiece.pointToInt(piece.getPosition()));
                }
            }
            );
        }
//        if (!possibleMoves.isEmpty()) {
//            for (Integer i : possibleMoves) {
//                
//                if (checkForOtherPieces(sahFrame.getPieceList(), i)) {
//                    System.out.println("Removing move: " + i);
//                    possibleMoves.remove(i);
//                }
//                
//            }
//        }
        
//        for (Integer i : possibleMoves) {
//            System.out.println("possible move: " + i);
//        }
        
        return possibleMoves;
    }

    private boolean checkForOtherPieces(List<ChessPiece> pieces, int move) {

        for (ChessPiece pic : pieces) {
            //System.out.println("All pawn pos " +ChessPiece.pointToInt(pic.getPosition()));
            if (ChessPiece.pointToInt(pic.getPosition()) == move) {
                return true; //if move is blocked
            }
        }

        return false; //if move isnt blocked
    }

}
