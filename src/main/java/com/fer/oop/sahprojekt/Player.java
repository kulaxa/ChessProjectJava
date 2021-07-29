/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.oop.sahprojekt;

/**
 *
 * @author mario
 */
public class Player {
    
    private String name;
    private Color color;

    public Player(String name) {
        this.name = name;
    }
    
    public void setStartingColorForPlayer( Color color){
        this.color = color;
        
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
    
    
}
