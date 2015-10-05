package silver.dan;

import java.awt.*;

public class Game {
    Player one, two;

    Game(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    public void play(Player firstToMove) {
        Player currentPlayer = firstToMove;
        Board board = new Board();

        while (board.checkGameOver() == Board.state.NONE) {
            System.out.println("Current Player: " + currentPlayer.getClass().getSimpleName() + " " + currentPlayer.player);
            Point move = currentPlayer.makeMove(board);
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
    // creates the board and starts the game
    public static void play (Player playerOne, Player playerTwo) {
        playerOne.setPlayerName(Board.state.X);
        playerTwo.setPlayerName(Board.state.O);

        Game game = new Game(playerOne, playerTwo);
        game.play(playerOne);
    }
}
