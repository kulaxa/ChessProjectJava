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
	 * To change this template file, choose Tools | Templates and open the template
	 * in the editor.
     */
    private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedRook;
    private boolean alreadyMoved;

    public King(Point position, Color color) {
        super(position, color);
        possibleMoves = new LinkedList<>();
        alreadyMoved = false;

    }

    public boolean getAlreadyMoved() {
        return alreadyMoved;
    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }
    
    
    @Override
    public void setPosition(Point point){
        super.setPosition(point);
        
    }

    @Override
    public List<Integer> getPossibleMoves() {
        return checkMoves();
    }

    private List<Integer> checkMoves() {

        possibleMoves.clear();
        int pos = ChessPiece.pointToInt(this.getPosition());
        Point pos2 = this.getPosition();
        List<Integer> lista = List.of(pos + 1, pos - 1, pos + 8, pos - 8, pos - 7, pos + 7, pos + 9, pos - 9);
        List<Point> lista2 = List.of(new Point(1, 0),
                new Point(-1, 0),
                new Point(0, 1),
                new Point(0, -1),
                new Point(1, 1),
                new Point(+1, -1),
                new Point(-1, 1),
                new Point(-1, -1)
        );

        for (Point p : lista2) {
            if (!isBeyondBounds(pos2, p)) {

                int i = ChessPiece.pointToInt(pos2) + p.getY() * 8 + p.getX();
                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                    if (((ChessPiece) sahFrame.getFieldList().get(i).getComponents()[0]).getColor() != this.getColor()) {
                        if (!checkIfKingInMat(p)) {
                            possibleMoves.add(i);
                        }
                    }
                } else {
                    if (!checkIfKingInMat(p)) {
                        possibleMoves.add(i);
                    }
                }
            }
        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 4) {
                if (sahFrame.getFieldList().get(7).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(7).getComponents()[0] instanceof Rook) {
                        if (!((Rook) (sahFrame.getFieldList().get(7).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 5; i < 7; i++) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                ((Rook) (sahFrame.getFieldList().get(7).getComponent(0))).setRosada(true);
                            }
                        }
                    }
                }
            }
        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 4) {
                if (sahFrame.getFieldList().get(0).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(0).getComponents()[0] instanceof Rook) {
                        if (!((Rook) (sahFrame.getFieldList().get(0).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 3; i > 0; i--) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                ((Rook) (sahFrame.getFieldList().get(0).getComponent(0))).setRosada(true);
                            }
                        }
                    }
                }
            }
        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 60) {
                if (sahFrame.getFieldList().get(63).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(63).getComponents()[0] instanceof Rook) {
                        if (!((Rook) (sahFrame.getFieldList().get(63).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 61; i < 63; i++) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                ((Rook) (sahFrame.getFieldList().get(63).getComponent(0))).setRosada(true);
                            }
                        }
                    }
                }
            }
        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 60) {
                if (sahFrame.getFieldList().get(56).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(56).getComponents()[0] instanceof Rook) {
                        if (!((Rook) (sahFrame.getFieldList().get(56).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 59; i > 56; i--) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                ((Rook) (sahFrame.getFieldList().get(56).getComponent(0))).setRosada(true);
                            }
                        }
                    }
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) { // ovo je komentar
    }

    private boolean isBeyondBounds(Point knightPoint, Point movePoint) {

        if (knightPoint.getX() + movePoint.getX() <= 7 && knightPoint.getX() + movePoint.getX() >= 0
                && knightPoint.getY() + movePoint.getY() <= 7 && knightPoint.getY() + movePoint.getY() >= 0) {

            return false;
        }

        return true;
    }

    private boolean checkIfKingInMat(Point p) {

        boolean kingInMat = false;
        Point currentPos = this.getPosition();
        Point nextPosiblePos = Point.addTwoPoints(currentPos, p);
        int i = ChessPiece.pointToInt(nextPosiblePos);
        ChessPiece posEaten = null;
        if (sahFrame.getFieldList().get(i).getComponents().length != 0 && ((ChessPiece) sahFrame.getFieldList().get(i).getComponents()[0]).getColor() != Game.getCurrentColor()) {
            posEaten = (ChessPiece) sahFrame.getFieldList().get(i).getComponents()[0];
            sahFrame.getFieldList().get(ChessPiece.pointToInt(nextPosiblePos)).remove(posEaten);
            sahFrame.getPieceList().remove(posEaten);

        }
        this.setPosition(nextPosiblePos);
        sahFrame.getFieldList().get(ChessPiece.pointToInt(nextPosiblePos)).add(this);
        sahFrame.getFieldList().get(ChessPiece.pointToInt(currentPos)).remove(this);
//        if(checkForChess()){
//            kingInMat = true;
//        }
        if (this.getColor() == Game.getCurrentColor()) {
            List<ChessPiece> tempList = sahFrame.getPieceList().stream().filter(pic -> pic.getColor() != Game.getCurrentColor()).collect(Collectors.toList());
            //System.out.println("King position: "+pointToInt(kingInChess.getPosition()));
            for (ChessPiece piece : tempList) {

                for (Integer in : piece.getPossibleMoves()) {

                    if (in == ChessPiece.pointToInt(nextPosiblePos)) {
                        System.out.println("remvoing king move: " + p);
                        kingInMat = true;
                    }
                }

            }
        }
        this.setPosition(currentPos);
        sahFrame.getFieldList().get(ChessPiece.pointToInt(currentPos)).add(this);
        sahFrame.getFieldList().get(ChessPiece.pointToInt(nextPosiblePos)).remove(this);

        if (posEaten != null) {
            sahFrame.getFieldList().get(ChessPiece.pointToInt(nextPosiblePos)).add(posEaten);
            sahFrame.getPieceList().add(posEaten);
        }

        return kingInMat;

    }

}
