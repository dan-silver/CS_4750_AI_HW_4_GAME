package silver.dan;

import java.awt.*;

public class Game {
    Player one, two;
    Board board;

    Game(Board board, Player one, Player two) {
        this.one = one;
        this.two = two;
        this.board = board;
    }

    public void play(Player firstToMove) {
        Player currentPlayer = firstToMove;

        while (board.checkGameOver() == Board.state.NONE) {
            System.out.println("Current Player: " + currentPlayer.getClass().getSimpleName() + " " + currentPlayer.player);
            Point move = currentPlayer.makeMove();
            board.setSpaceStatus(move, currentPlayer.player);

            board.printBoard();
            currentPlayer = switchPlayer(currentPlayer);
        }
        System.out.println("Game Winner: " + board.checkGameOver());
    }

    private Player switchPlayer(Player p) {
        return p == one ? two : one;
    }

    // player ONE moves first
    public static void play (Player playerOne, Player playerTwo) {
        Board board = new Board();

        playerOne.setBoardAndPlayer(board, Board.state.X);
        playerTwo.setBoardAndPlayer(board, Board.state.O);

        Game game = new Game(board, playerOne, playerTwo);
        game.play(playerOne);
    }
}
