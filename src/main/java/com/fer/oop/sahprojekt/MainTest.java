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
public class MainTest {
    public static void main(String[] args) {
        
        
        Point point = new Point(7,7);
        int cord = 63;
        
        System.out.println(ChessPiece.pointToInt(point));
        System.out.println(ChessPiece.intToPoint(cord));
    }
}
