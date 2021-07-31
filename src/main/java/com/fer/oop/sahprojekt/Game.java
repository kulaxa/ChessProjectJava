/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

import java.util.Random;

/**
 *
 * @author mario
 */
public class Game {
    
    private static Color playerTurn;
    private static Color prevColor;
    private Player player1, player2;
    
    
    
    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2= player2;
        playerTurn = Color.WHITE;
        prevColor = Color.BLACK;
        
    }
    public static Color getCurrentColor(){
        
        return playerTurn;
    }
    
    
    private Player drawRandomPlayer(Player player1, Player player2){
        
        int randomNum =0;
        Random random = new Random(1);
        randomNum = random.nextInt();
        if(randomNum ==0) return player1;
        return player2;
    }
    
    private void setStartingColor(){
        
        drawRandomPlayer(player1, player2).setStartingColorForPlayer(Color.WHITE);
        
        if(player1.getColor() == null) player1.setStartingColorForPlayer(Color.BLACK);
        if(player2.getColor() == null) player2.setStartingColorForPlayer(Color.BLACK);
  
    }
    
    
    public static Color getNextTurnColor(){
        
        Color temp = playerTurn;
        playerTurn = prevColor;
        prevColor= temp;
        
        
        //System.out.println("promijenila se boja");
        
        
        
        return temp;
    }
    
   
}
