/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author mario
 */
public class Rook extends ChessPiece {

    private List<Integer> possibleMoves;
    private ImageIcon icon;
    private ChessPiece clickedRook;
    private boolean alreadyMoved;
    private boolean rosada;

    public Rook(Point position, Color color) {
        super(position, color);
        possibleMoves = new LinkedList<>();
        alreadyMoved = false;
        rosada = false;

    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public boolean getAlreadyMoved() {
        return alreadyMoved;
    }

    public boolean isRosada() {
        return rosada;
    }

    public void setRosada(boolean rosada) {
        this.rosada = rosada;;
    }

    @Override
    public List<Integer> getPossibleMoves() {

        System.out.println(" ");

        return checkMoves();
    }

    private List<Integer> checkMoves() {


        System.out.println("CheckMoves");
        // int position = ChessPiece.pointToInt(this.getPosition());


        int left = 0, right = 0, up = 0, down = 0;

        boolean switchSide = false;

        possibleMoves.clear();

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

        
        if(!alreadyMoved) {
        	if (ChessPiece.pointToInt(this.getPosition()) == 0) {
        		if (!((King) (sahFrame.getFieldList().get(4).getComponent(0))).getAlreadyMoved()) {
        			int j = 0;
        			for(int i = 1; i < 4; i++) {
            			if(sahFrame.getFieldList().get(i).getComponents().length != 0)
            				j++;
            		}
        			if(j == 0) {
        				rosada = true;
        				System.out.println("rosada: " + rosada);
        			}
        			
        		}
        		
        		
        	}
        	


        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 0) {
                if (sahFrame.getFieldList().get(4).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(4).getComponents()[0] instanceof King) {
                        if (!((King) (sahFrame.getFieldList().get(4).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 1; i < 4; i++) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                rosada = true;
                                System.out.println("rosada: " + rosada);
                            }

                        }
                    }
                }

            }


        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 7) {
                if (sahFrame.getFieldList().get(4).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(4).getComponents()[0] instanceof King) {
                        if (!((King) (sahFrame.getFieldList().get(4).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 6; i > 4; i--) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                rosada = true;
                                System.out.println("rosada: " + rosada);
                            }
                        }
                    }
                }
            }

        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 56) {
                if (sahFrame.getFieldList().get(60).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(60).getComponents()[0] instanceof King) {
                        if (!((King) (sahFrame.getFieldList().get(60).getComponent(0))).getAlreadyMoved()) {
                            int j = 0;
                            for (int i = 57; i < 60; i++) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                rosada = true;
                                System.out.println("rosada: " + rosada);
                            }
                        }
                    }
                }

            }
        }
        if (!alreadyMoved) {
            if (ChessPiece.pointToInt(this.getPosition()) == 63) {
                if (sahFrame.getFieldList().get(60).getComponents().length != 0) {
                    if (sahFrame.getFieldList().get(60).getComponents()[0] instanceof King) {
                        if (!((King) (sahFrame.getFieldList().get(60).getComponent(0))).getAlreadyMoved()) {

                            int j = 0;
                            for (int i = 62; i > 60; i--) {
                                if (sahFrame.getFieldList().get(i).getComponents().length != 0) {
                                    j++;
                                }
                            }
                            if (j == 0) {
                                rosada = true;
                                System.out.println("rosada: " + rosada);
                            }
                        }
                    }
                }
            }

        }

        if(!alreadyMoved) {
        	if (ChessPiece.pointToInt(this.getPosition()) == 63) {
        		if (!((King) (sahFrame.getFieldList().get(60).getComponent(0))).getAlreadyMoved()) {
        			
        			int j = 0;
            		for(int i = 62; i > 60; i--) {
            			if(sahFrame.getFieldList().get(i).getComponents().length != 0)
            				j++;
            		}
            		if(j == 0) {
        				rosada = true;
        				System.out.println("rosada: " + rosada);
        		}
        		}
        	}
        		
        		
        	
        	}
        	
        
        }

        return possibleMoves;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        ChessPiece paneltemp = (ChessPiece) e.getComponent(); //može provjera jeli japenl
        
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
