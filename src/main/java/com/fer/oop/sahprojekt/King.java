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
                        possibleMoves.add(i);
                    }
                } else {
                    possibleMoves.add(i);
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

}
