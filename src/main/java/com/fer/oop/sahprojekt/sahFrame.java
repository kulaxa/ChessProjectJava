/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jelena
 */
public class sahFrame extends JFrame {

    private JPanel board = new JPanel();
    private static List<JPanel> fieldList = new ArrayList<>();
    private static List<ChessPiece> pieceList = new ArrayList<>();

    private JPanel eatenPiecesPanel = new JPanel();
    private JPanel eatenPiecesWhitePanel = new JPanel();
    private JPanel eatenPiecesBlackPanel = new JPanel();
    private List<ChessPiece> eatenPiecesList = new ArrayList<>();

    public static List<ChessPiece> getPieceList() {
        return pieceList;
    }
    private JPanel clickedPanel;
    private JPanel currentPanel = null;
    private ChessPiece clickedPiece;
    private List<Integer> possibleMoves;
    private boolean isPieceSelected = false;
    private boolean sah;

    public static List<JPanel> getFieldList() {
        return fieldList;
    }

    public sahFrame() throws IOException, InterruptedException, InvocationTargetException {
        Color myBrown = new Color(191, 152, 90);
        board.setLayout(new GridLayout(8, 8));
        sah = false;
        // board.setSize(800,800);
        for (int i = 0; i < 8; i++) {
        	
            for (int j = 0; j < 8; j++) {
                JPanel field = new JPanel();

                field.setSize(40, 40);
                field.setBackground(myBrown);

                fieldList.add(field);
                if ((i + j) % 2 == 0) {

                    field.setBackground(Color.black);

                    field.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if ((((JPanel) e.getSource()).getComponents().length != 0) && isPieceSelected == false) {
                                clickedPiece = (ChessPiece) (((JPanel) e.getSource()).getComponents()[0]);

                                if (clickedPiece.getColor() == Game.getCurrentColor()) {

                                    currentPanel = (JPanel) e.getSource();
                                    isPieceSelected = true;
                                    possibleMoves = clickedPiece.getPossibleMoves();

                                    if (clickedPiece instanceof Rook) {
                                        if (((Rook) clickedPiece).isRosada()) {

                                            fieldList.get(ChessPiece.pointToInt((pieceList.stream().filter(piece -> piece instanceof King).filter(piece -> piece.getColor() == Game.getCurrentColor()).findFirst()).get().getPosition())).setBackground(Color.GREEN);

                                        }
                                    }

                                    currentPanel = (JPanel) e.getSource(); //new code

                                    isPieceSelected = true;
                                    possibleMoves = clickedPiece.getPossibleMoves();

                                    if (!possibleMoves.isEmpty()) {
                                        for (Integer i : possibleMoves) {

                                            fieldList.get(i).setBackground(Color.BLUE);

                                        }
                                    }
                                }

                            } else {
                                if (clickedPiece != null && isPieceSelected == true) {

                                    clickedPanel = (JPanel) e.getComponent();
                                    if (clickedPiece instanceof Rook) {
                                        if (clickedPanel.getComponents().length != 0) {
                                            if (clickedPanel.getComponent(0) instanceof King) {
                                                if (((Rook) clickedPiece).isRosada() && clickedPiece.getColor() == Game.getCurrentColor()) {

                                                    isPieceSelected = false;

                                                    Point posi = clickedPiece.getPosition();
                                                    if (clickedPiece.getPosition().getX() == 0) {

                                                        fieldList.get(ChessPiece.pointToInt(posi) + 3).add(clickedPiece);
                                                        clickedPiece.setPosition(new Point(posi.getX() + 3, posi.getY()));
                                                        //kriva boja radi rošadu, kriva boja je na potezu nakon rošade
                                                        ChessPiece tempKing
                                                                = pieceList.stream().filter(pic -> pic instanceof King).filter(pic -> pic.getColor() == Game.getCurrentColor()).findAny().get();
                                                        fieldList.get(ChessPiece.pointToInt(posi) + 2).add(tempKing);
                                                        tempKing.setPosition(new Point(tempKing.getPosition().getX() - 2, tempKing.getPosition().getY()));
                                                        if (tempKing instanceof King) {
                                                            ((King) tempKing).setAlreadyMoved(true);
                                                        }

                                                    } else {

                                                        fieldList.get(ChessPiece.pointToInt(clickedPiece.getPosition()) - 2).add(clickedPiece);
                                                        clickedPiece.setPosition(new Point(posi.getX() - 2, posi.getY()));
                                                        ChessPiece tempKing
                                                                = pieceList.stream().filter(pic -> pic instanceof King).filter(pic -> pic.getColor() == Game.getCurrentColor()).findAny().get();
                                                        fieldList.get(ChessPiece.pointToInt(tempKing.getPosition()) + 2).add(tempKing);
                                                        tempKing.setPosition(new Point(tempKing.getPosition().getX() + 2, tempKing.getPosition().getY()));

                                                        if (tempKing instanceof King) {
                                                            ((King) tempKing).setAlreadyMoved(true);
                                                        }
                                                    }

                                                    ((Rook) clickedPiece).setRosada(false);

                                                    Game.getNextTurnColor();
                                                    for(ChessPiece ch: pieceList) {
                                                    	for(Integer k: ch.getPossibleMoves()) {
                                                    		if(sahFrame.getFieldList().get(k).getComponents().length != 0) {
                                                    			if(((ChessPiece) (sahFrame.getFieldList().get(k).getComponent(0))) instanceof King) {
                                                    				sah = true;
                                                    			}
                                                    		}
                                                    	}
                                                	}
                                                
                                                    	System.out.println("sah :" + sah);
                                            
                                                
                                            }
                                        }
                                    }
                                    if (!possibleMoves.isEmpty()) {
                                        for (Integer i : possibleMoves) {

                                            if (ChessPiece.intToPoint(i).getX() % 2 == 0) {
                                                if (ChessPiece.intToPoint(i).getY() % 2 != 0) {
                                                    fieldList
                                                            .get(i).setBackground(myBrown);
                                                } else {
                                                    fieldList.get(i).setBackground(Color.BLACK);
                                                }
                                            } else {
                                                if (ChessPiece.intToPoint(i).getY() % 2 == 0) {
                                                    fieldList.get(i).setBackground(myBrown);
                                                } else {
                                                    fieldList.get(i).setBackground(Color.BLACK);
                                                }
                                            }
                                        }
                                        for (Integer i : possibleMoves) {

                                            if (fieldList.indexOf(clickedPanel) == i) {
                                                if (clickedPiece instanceof Pawn) {
                                                    Pawn temp = (Pawn) clickedPiece;
                                                    temp.setAlreadyMoved(true);
                                                }
                                                if (clickedPiece instanceof Rook) {
                                                    Rook temp = (Rook) clickedPiece;
                                                    temp.setAlreadyMoved(true);
                                                }
                                                if (clickedPiece instanceof King) {
                                                    King temp = (King) clickedPiece;
                                                    temp.setAlreadyMoved(true);
                                                }

                                                clickedPanel.add(clickedPiece);
                                                Game.getNextTurnColor();
                                                for(ChessPiece ch: pieceList) {
                                                	for(Integer k: ch.getPossibleMoves()) {
                                                		if(sahFrame.getFieldList().get(k).getComponents().length != 0) {
                                                			if(((ChessPiece) (sahFrame.getFieldList().get(k).getComponent(0))) instanceof King) {
                                                				sah = true;
                                                			}
                                                		}
                                                	}
                                            	}
                                            
                                                	System.out.println("sah :" + sah);
                                        

                                                clickedPiece.setPosition(ChessPiece.intToPoint(fieldList.indexOf(clickedPanel)));

                                                clickedPanel.revalidate();

                                                if (clickedPanel.getComponents().length != 0) {
                                                    ChessPiece eatenPiece = (ChessPiece) clickedPanel.getComponents()[0];
                                                    if (eatenPiece != clickedPiece) {
                                                        pieceList.remove(eatenPiece);
                                                        clickedPanel.remove(eatenPiece);
                                                        eatenPiecesList.add(eatenPiece);
                                                        refreshEatenPanel();

                                                    }

                                                }
                                                isPieceSelected = false;

//                                          
                                            } else {
                                                isPieceSelected = false;
                                            }
//                                       
                                        }
                                    } else {
                                        isPieceSelected = false;
                                    }

                                }

                            }
                           

                            for (JPanel fl : fieldList) {

                                fl.revalidate();
                                fl.repaint();
                            }

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            field.revalidate();
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (field.getBackground() != Color.BLUE) {
                                field.setBackground(Color.RED);
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (field.getBackground() != Color.BLUE) {
                                field.setBackground(Color.black);
                            }
                        }
                    });

                } else {

                    field.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if ((((JPanel) e.getSource()).getComponents().length != 0) && isPieceSelected == false) {
                                clickedPiece = (ChessPiece) (((JPanel) e.getSource()).getComponents()[0]);

                                if (clickedPiece.getColor() == Game.getCurrentColor()) {
                                    isPieceSelected = true;
                                    possibleMoves = clickedPiece.getPossibleMoves();

                                    if (!possibleMoves.isEmpty()) {
                                        for (Integer i : possibleMoves) {

                                            fieldList.get(i).setBackground(Color.BLUE);

                                        }
                                    }
                                }

                            } else {
                                if (clickedPiece != null && isPieceSelected == true) {

                                    clickedPanel = (JPanel) e.getComponent();

                                    if (clickedPiece instanceof Rook) {
                                        if (clickedPanel.getComponents().length != 0) {
                                            if (clickedPanel.getComponent(0) instanceof King) {
                                                if (((Rook) clickedPiece).isRosada() && clickedPiece.getColor() == Game.getCurrentColor()) {

                                                    isPieceSelected = false;

                                                    Point posi = clickedPiece.getPosition();
                                                    if (clickedPiece.getPosition().getX() == 0) {

                                                        fieldList.get(ChessPiece.pointToInt(posi) + 3).add(clickedPiece);
                                                        clickedPiece.setPosition(new Point(posi.getX() + 3, posi.getY()));
                                                        //kriva boja radi rošadu, kriva boja je na potezu nakon rošade
                                                        ChessPiece tempKing
                                                                = pieceList.stream().filter(pic -> pic instanceof King).filter(pic -> pic.getColor() == Game.getCurrentColor()).findAny().get();
                                                        fieldList.get(ChessPiece.pointToInt(posi) + 2).add(tempKing);
                                                        tempKing.setPosition(new Point(tempKing.getPosition().getX() - 2, tempKing.getPosition().getY()));
                                                        if (tempKing instanceof King) {
                                                            ((King) tempKing).setAlreadyMoved(true);
                                                        }

                                                    } else {

                                                        fieldList.get(ChessPiece.pointToInt(clickedPiece.getPosition()) - 2).add(clickedPiece);
                                                        clickedPiece.setPosition(new Point(posi.getX() - 2, posi.getY()));
                                                        ChessPiece tempKing
                                                                = pieceList.stream().filter(pic -> pic instanceof King).filter(pic -> pic.getColor() == Game.getCurrentColor()).findAny().get();
                                                        fieldList.get(ChessPiece.pointToInt(tempKing.getPosition()) + 2).add(tempKing);
                                                        tempKing.setPosition(new Point(tempKing.getPosition().getX() + 2, tempKing.getY()));
                                                        if (tempKing instanceof King) {
                                                            ((King) tempKing).setAlreadyMoved(true);
                                                        }
                                                    }
                                                    ((Rook) clickedPiece).setRosada(false);
                                                    Game.getNextTurnColor();
                                                    for(ChessPiece ch: pieceList) {
                                                    	for(Integer k: ch.getPossibleMoves()) {
                                                    		if(sahFrame.getFieldList().get(k).getComponents().length != 0) {
                                                    			if(((ChessPiece) (sahFrame.getFieldList().get(k).getComponent(0))) instanceof King) {
                                                    				sah = true;
                                                    			}
                                                    		}
                                                    	}
                                                	}
                                                
                                                    	System.out.println("sah :" + sah);
                                            
                                                }
                                            }
                                        }
                                    }
                                    if (!possibleMoves.isEmpty()) {
                                        for (Integer i : possibleMoves) {

                                            if (ChessPiece.intToPoint(i).getX() % 2 == 0) {
                                                if (ChessPiece.intToPoint(i).getY() % 2 != 0) {
                                                    fieldList
                                                            .get(i).setBackground(myBrown);
                                                } else {
                                                    fieldList.get(i).setBackground(Color.BLACK);
                                                }
                                            } else {
                                                if (ChessPiece.intToPoint(i).getY() % 2 == 0) {
                                                    fieldList.get(i).setBackground(myBrown);
                                                } else {
                                                    fieldList.get(i).setBackground(Color.BLACK);
                                                }
                                            }
                                        }

                                        for (Integer i : possibleMoves) {
//                                             

                                            if (fieldList.indexOf(clickedPanel) == i) {
                                                if (clickedPiece instanceof Pawn) {
                                                    Pawn temp = (Pawn) clickedPiece;
                                                    temp.setAlreadyMoved(true);
                                                }
                                                if (clickedPiece instanceof Rook) {
                                                    Rook temp = (Rook) clickedPiece;
                                                    temp.setAlreadyMoved(true);
                                                }
                                                if (clickedPiece instanceof King) {
                                                    King temp = (King) clickedPiece;
                                                    temp.setAlreadyMoved(true);
                                                }
                                                clickedPanel.add(clickedPiece);

                                                Game.getNextTurnColor();

                                              //  System.out.println("chess: "+ ChessPiece.checkForChess());

                                                for(ChessPiece ch: pieceList) {
                                                	for(Integer k: ch.getPossibleMoves()) {
                                                		if(sahFrame.getFieldList().get(k).getComponents().length != 0) {
                                                			if(((ChessPiece) (sahFrame.getFieldList().get(k).getComponent(0))) instanceof King) {
                                                				sah = true;
                                                			}
                                                		}
                                                	}
                                            	}
                                            
                                                	System.out.println("sah :" + sah);
                                        
                                                



                                                clickedPiece.setPosition(ChessPiece.intToPoint(fieldList.indexOf(clickedPanel)));
                                                clickedPanel.revalidate();

                                                if (clickedPanel.getComponents().length != 0) {

                                                    ChessPiece eatenPiece = (ChessPiece) clickedPanel.getComponents()[0];
                                                    if (eatenPiece != clickedPiece) {
                                                        pieceList.remove(eatenPiece);
                                                        clickedPanel.remove(eatenPiece);
                                                        eatenPiecesList.add(eatenPiece);
                                                        refreshEatenPanel();

                                                    }
                                                }
                                                isPieceSelected = false;
                                            } else {
                                                isPieceSelected = false;

                                            }

                                        }
                                    } else {
                                        isPieceSelected = false;
                                    }

                                }
                            }
                            for (JPanel fl : fieldList) {
                                fl.revalidate();
                                fl.repaint();
                            }

                            //0, 2, 4, 6
                            //9, 11, 13, 15,
                            //16, 18, 20, 22
                            //
                        }

                        @Override
                        public void mousePressed(MouseEvent e
                        ) {
                            //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseReleased(MouseEvent e
                        ) {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            field.revalidate();
                        }

                        @Override
                        public void mouseEntered(MouseEvent e
                        ) {
                            if (field.getBackground() != Color.BLUE) {
                                field.setBackground(Color.RED);
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e
                        ) { //ovo je komentar
                            if (field.getBackground() != Color.BLUE) {
                                field.setBackground(myBrown);
                            }
                        }
                    }
                    );
                }

                board.add(field);

            }
        }
        

        }

        addPiecesToBoard();
        //setTestPieces();

      


        eatenPiecesPanel.setLayout(new GridLayout(2, 0));
        eatenPiecesWhitePanel.setBackground(Color.WHITE);
        eatenPiecesBlackPanel.setBackground(Color.BLACK);
        eatenPiecesWhitePanel.setPreferredSize(new Dimension(400, 0));
        eatenPiecesBlackPanel.setPreferredSize(new Dimension(400, 0));

        eatenPiecesPanel.add(eatenPiecesWhitePanel);
        eatenPiecesPanel.add(eatenPiecesBlackPanel);

        add(eatenPiecesPanel, BorderLayout.EAST);
        add(board);

    }

    private void refreshEatenPanel() {

//        eatenPiecesBlackPanel.removeAll();
//        eatenPiecesWhitePanel.removeAll();
        int numOfEatenPiecesWhite
                = (int) eatenPiecesList.stream().filter(piece -> piece.getColor() == com.fer.oop.sahprojekt.Color.WHITE).count();
        int numOfEatenPiecesBlack
                = (int) eatenPiecesList.stream().filter(piece -> piece.getColor() == com.fer.oop.sahprojekt.Color.BLACK).count();

        System.out.println("eaten pieces: white: " + numOfEatenPiecesWhite + " black: " + numOfEatenPiecesBlack);
        if (numOfEatenPiecesBlack != 0) {
            eatenPiecesBlackPanel.setLayout(new GridLayout(0, numOfEatenPiecesWhite / 3 + 1));
            System.out.println("Setting grid: " + "0 ," + (numOfEatenPiecesWhite / 3 + 1));
        }
        if (numOfEatenPiecesWhite != 0) {
            eatenPiecesWhitePanel.setLayout(new GridLayout(0, numOfEatenPiecesBlack / 3 + 1));
            System.out.println("Setting grid: " + "0 ," + (numOfEatenPiecesBlack / 3 + 1));
        }
        for (ChessPiece pic : eatenPiecesList) {
            if (pic.getColor() == com.fer.oop.sahprojekt.Color.BLACK) {

                eatenPiecesWhitePanel.add(pic);
            } else {
                eatenPiecesBlackPanel.add(pic);
            }
        }

    }

    private void addPiecesToBoard() throws IOException {
        //add pawns
        int labelWidth = fieldList.get(0).getWidth();
        int labelHeight = fieldList.get(0).getHeight();
        for (int j = 0; j < 2; j++) {

            File filewhite = new File("whtepawn - copy.png");
            BufferedImage imagewhite = ImageIO.read(filewhite);
            ImageIcon whiteicon = new ImageIcon(imagewhite);

            File fileblack = new File("black_pawn.png");
            BufferedImage imageblack = ImageIO.read(fileblack);
            ImageIcon blackicon = new ImageIcon(imageblack);

            File filerookWhite = new File("rook_white.png");
            BufferedImage imageRookWhite = ImageIO.read(filerookWhite);
            ImageIcon iconRookWhite = new ImageIcon(imageRookWhite);

            File filerookBlack = new File("rook_black.png");
            BufferedImage imageRookBlack = ImageIO.read(filerookBlack);
            ImageIcon iconRookBlack = new ImageIcon(imageRookBlack);

            File fileBishopWhite = new File("bishop_white.png");
            BufferedImage imageBishopWhite = ImageIO.read(fileBishopWhite);
            ImageIcon iconBishopWhite = new ImageIcon(imageBishopWhite);

            File fileBishopBlack = new File("bishop_black.png");
            BufferedImage imageBishopBlack = ImageIO.read(fileBishopBlack);
            ImageIcon iconBishopBlack = new ImageIcon(imageBishopBlack);

            File fileQueenBlack = new File("queen_black.png");
            BufferedImage imageQueenBlack = ImageIO.read(fileQueenBlack);
            ImageIcon iconQueenBlack = new ImageIcon(imageQueenBlack);

            File fileQueenWhite = new File("queen_white.png");
            BufferedImage imageQueenWhite = ImageIO.read(fileQueenWhite);
            ImageIcon iconQueenWhite = new ImageIcon(imageQueenWhite);

            File fileKingBlack = new File("king_black.png");
            BufferedImage imageKingBlack = ImageIO.read(fileKingBlack);
            ImageIcon iconKingBlack = new ImageIcon(imageKingBlack);

            File fileKingWhite = new File("king_white.png");
            BufferedImage imageKingWhite = ImageIO.read(fileKingWhite);
            ImageIcon iconKingWhite = new ImageIcon(imageKingWhite);

            File fileKnightWhite = new File("knight_white.png");
            BufferedImage imageKnightWhite = ImageIO.read(fileKnightWhite);
            ImageIcon iconKnightWhite = new ImageIcon(imageKnightWhite);

            File fileKnightBlack = new File("knight_black.png");
            BufferedImage imageKnightBlack = ImageIO.read(fileKnightBlack);
            ImageIcon iconKnightBlack = new ImageIcon(imageKnightBlack);


                if (j == 0) {
                    Pawn pawn = new Pawn(new Point(i, 1), com.fer.oop.sahprojekt.Color.WHITE);

                    pawn.setIcon(whiteicon);
                    pieceList.add(pawn);
                    fieldList.get(8 + i).add(pawn);
                } else if (j == 1) {

                    Pawn pawn = new Pawn(new Point(i, 6), com.fer.oop.sahprojekt.Color.BLACK);

                    pawn.setIcon(blackicon);
                    pieceList.add(pawn);
                    fieldList.get(48 + i).add(pawn);
                }
            
            if (j == 0) {
                Rook rook = new Rook(new Point(0, 0), com.fer.oop.sahprojekt.Color.WHITE);
                rook.setIcon(iconRookWhite);
                pieceList.add(rook);
                fieldList.get(0 + 0).add(rook);

                rook = new Rook(new Point(7, 0),
                        com.fer.oop.sahprojekt.Color.WHITE);
                rook.setIcon(iconRookWhite);
                pieceList.add(rook);
                fieldList.get(0 + 7).add(rook);
            }
            if (j == 1) {
                Rook rook = new Rook(new Point(0, 7), com.fer.oop.sahprojekt.Color.BLACK);
                rook.setIcon(iconRookBlack);
                pieceList.add(rook);
                fieldList.get(56 + 0).add(rook);

                rook = new Rook(new Point(7, 7), com.fer.oop.sahprojekt.Color.BLACK);
                rook.setIcon(iconRookBlack);
                pieceList.add(rook);
                fieldList.get(56 + 7).add(rook);
            }
            if (j == 0) {
                    Bishop bishop = new Bishop(new Point(2,0), com.fer.oop.sahprojekt.Color.WHITE);
                    bishop.setIcon(iconBishopWhite);
                    pieceList.add(bishop);
                    fieldList.get(2).add(bishop);
                    
                     bishop = new Bishop(new Point(5,0), com.fer.oop.sahprojekt.Color.WHITE);
                    bishop.setIcon(iconBishopWhite);
                    pieceList.add(bishop);
                    fieldList.get(5).add(bishop);

            }
            if (j == 1) {
                     Bishop bishop = new Bishop(new Point(2,7), com.fer.oop.sahprojekt.Color.BLACK);
                    bishop.setIcon(iconBishopBlack);
                    pieceList.add(bishop);
                    fieldList.get(7*8+2).add(bishop);
                    
                     bishop = new Bishop(new Point(5,7), com.fer.oop.sahprojekt.Color.BLACK);
                    bishop.setIcon(iconBishopBlack);
                    pieceList.add(bishop);
                    fieldList.get(7*8+5).add(bishop);
            }

            if (j == 0) {

                    Queen queen = new Queen(new Point(3,0), com.fer.oop.sahprojekt.Color.WHITE);
                    queen.setIcon(iconQueenWhite);
                    pieceList.add(queen);
                    fieldList.get(3).add(queen);
            }
            if (j == 1) {
                     Queen queen = new Queen(new Point(3,7), com.fer.oop.sahprojekt.Color.BLACK);
                    queen.setIcon(iconQueenBlack);
                    pieceList.add(queen);
                    fieldList.get(8*7 +3).add(queen);
            }
            if (j == 0) {

                King king = new King(new Point(4, 0), com.fer.oop.sahprojekt.Color.WHITE);
                king.setIcon(iconKingWhite);
                pieceList.add(king);
                fieldList.get(4).add(king);

            }
            if (j == 1) {
                King king = new King(new Point(4, 7), com.fer.oop.sahprojekt.Color.BLACK);
                king.setIcon(iconKingBlack);
                pieceList.add(king);
                fieldList.get(8 * 7 + 4).add(king);
            }

            if (j == 0) {
                    Knight knight = new Knight(new Point(1,0), com.fer.oop.sahprojekt.Color.WHITE);
                    knight.setIcon(iconKnightWhite);
                    pieceList.add(knight);
                    fieldList.get(1).add(knight);
                    
                     knight = new Knight(new Point(6,0), com.fer.oop.sahprojekt.Color.WHITE);
                    knight.setIcon(iconKnightWhite);
                    pieceList.add(knight);
                    fieldList.get(6).add(knight);
            }

            if (j == 1) {
                    Knight knight = new Knight(new Point(1,7), com.fer.oop.sahprojekt.Color.BLACK);
                    knight.setIcon(iconKnightBlack);
                    pieceList.add(knight);
                    fieldList.get(1+7*8).add(knight);
                    
                     knight = new Knight(new Point(6,7), com.fer.oop.sahprojekt.Color.BLACK);
                    knight.setIcon(iconKnightBlack);
                    pieceList.add(knight);
                    fieldList.get(6+7*8).add(knight);
            }
            
        }

        

    }
    
    private void setTestPieces() throws IOException{
          File filewhite = new File("whtepawn - copy.png");
            BufferedImage imagewhite = ImageIO.read(filewhite);
            ImageIcon whiteicon = new ImageIcon(imagewhite);

            File fileblack = new File("black_pawn.png");
            BufferedImage imageblack = ImageIO.read(fileblack);
            ImageIcon blackicon = new ImageIcon(imageblack);

            File filerookWhite = new File("rook_white.png");
            BufferedImage imageRookWhite = ImageIO.read(filerookWhite);
            ImageIcon iconRookWhite = new ImageIcon(imageRookWhite);

            File filerookBlack = new File("rook_black.png");
            BufferedImage imageRookBlack = ImageIO.read(filerookBlack);
            ImageIcon iconRookBlack = new ImageIcon(imageRookBlack);

            File fileBishopWhite = new File("bishop_white.png");
            BufferedImage imageBishopWhite = ImageIO.read(fileBishopWhite);
            ImageIcon iconBishopWhite = new ImageIcon(imageBishopWhite);

            File fileBishopBlack = new File("bishop_black.png");
            BufferedImage imageBishopBlack = ImageIO.read(fileBishopBlack);
            ImageIcon iconBishopBlack = new ImageIcon(imageBishopBlack);

            File fileQueenBlack = new File("queen_black.png");
            BufferedImage imageQueenBlack = ImageIO.read(fileQueenBlack);
            ImageIcon iconQueenBlack = new ImageIcon(imageQueenBlack);

            File fileQueenWhite = new File("queen_white.png");
            BufferedImage imageQueenWhite = ImageIO.read(fileQueenWhite);
            ImageIcon iconQueenWhite = new ImageIcon(imageQueenWhite);

            File fileKingBlack = new File("king_black.png");
            BufferedImage imageKingBlack = ImageIO.read(fileKingBlack);
            ImageIcon iconKingBlack = new ImageIcon(imageKingBlack);

            File fileKingWhite = new File("king_white.png");
            BufferedImage imageKingWhite = ImageIO.read(fileKingWhite);
            ImageIcon iconKingWhite = new ImageIcon(imageKingWhite);

            File fileKnightWhite = new File("knight_white.png");
            BufferedImage imageKnightWhite = ImageIO.read(fileKnightWhite);
            ImageIcon iconKnightWhite = new ImageIcon(imageKnightWhite);

            File fileKnightBlack = new File("knight_black.png");
            BufferedImage imageKnightBlack = ImageIO.read(fileKnightBlack);
            ImageIcon iconKnightBlack = new ImageIcon(imageKnightBlack);
            
            
            King king = new King(ChessPiece.intToPoint(19), com.fer.oop.sahprojekt.Color.WHITE);           
                king.setIcon(iconKingWhite);
                pieceList.add(king);
                fieldList.get(19).add(king);
                
                Knight knight = new Knight(new Point(6,5), com.fer.oop.sahprojekt.Color.BLACK);
                    knight.setIcon(iconKnightBlack);
                    pieceList.add(knight);
                    fieldList.get(6+5*8).add(knight);
                    
                    Queen queen = new Queen(new Point(3,5), com.fer.oop.sahprojekt.Color.BLACK);
                    queen.setIcon(iconQueenBlack);
                    pieceList.add(queen);
                    fieldList.get(8*5 +3).add(queen);
                
                     Bishop bishop = new Bishop(new Point(2,5), com.fer.oop.sahprojekt.Color.WHITE);
                    bishop.setIcon(iconBishopWhite);
                    pieceList.add(bishop);
                    fieldList.get(2+5*8).add(bishop);
                    
                     bishop = new Bishop(new Point(4,5), com.fer.oop.sahprojekt.Color.WHITE);
                    bishop.setIcon(iconBishopWhite);
                    pieceList.add(bishop);
                    fieldList.get(4+5*8).add(bishop);
                
                
        
    }

    private Image fitimage(Image img, int w, int h) {
        BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedimage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return resizedimage;
    }

}


//ovo je komentar da vidimo kako radi merganje i takve stvari
//ovo je novi komentenar da vidimo kako radi merganje i takve stvari