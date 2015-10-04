package silver.dan;

import java.awt.*;

public class Game {
    Player one, two;

    Game(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    public void play() {
        Board board = new Board();
        while (true) {
            Point onesMove = one.makeMove();
            board.setSpaceStatus(onesMove, one.player);

            if (board.checkGameOver() != Board.state.NONE)
                break;

            Point twosMove = two.makeMove();
            board.setSpaceStatus(twosMove, two.player);

            if (board.checkGameOver() != Board.state.NONE)
                break;


            board.printBoard();
        }
        System.out.println("Game Winner: " + board.checkGameOver());
    }
}
