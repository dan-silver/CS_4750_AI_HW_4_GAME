package silver.dan;

import java.awt.*;

abstract public class Player {
    Board.state player; // X or O
    Board.state opponent; // X or O
    Board board;

    public void setBoardAndPlayerName(Board board, Board.state player) {
        this.player = player; // X or O
        this.opponent = this.player == Board.state.X ? Board.state.O : Board.state.X;
        this.board = board;
    }

    abstract public Point makeMove();
}
