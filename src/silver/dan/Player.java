package silver.dan;

import java.awt.*;

abstract public class Player {
    Board.state player; // X or O
    Board.state opponent; // X or O

    public void setPlayerName(Board.state player) {
        this.player = player; // X or O
        this.opponent = this.player == Board.state.X ? Board.state.O : Board.state.X;
    }

    abstract public Point makeMove(Board board);
}
