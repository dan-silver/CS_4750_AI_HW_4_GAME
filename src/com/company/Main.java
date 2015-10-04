package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        BoardTests.runTests();

        Board board = new Board();

        BeginnerDecision beginner2 = new BeginnerDecision(board, Board.state.O);

        while(board.checkGameOver() == Board.state.NONE) {
            Point p = beginner2.makeMove();
            board.setSpaceStatus(p, beginner2.player);
            System.out.println("moving");
        }


        System.out.println("GAME STATUS: " + board.checkGameOver());
    }
}
