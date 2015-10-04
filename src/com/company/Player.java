package com.company;

/**
 * Created by dan on 10/3/15.
 */
public class Player {
    Board.state player; // X or O
    Board.state opponent; // X or O
    Board board;

    Player(Board board, Board.state player) {
        this.player = player; // X or O
        this.opponent = this.player == Board.state.X ? Board.state.O : Board.state.X;
        this.board = board;

    }
}
