package silver.dan;

import java.awt.*;

public class Game {
    Player one, two;

    Game(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    public Player play(Player firstToMove, boolean showOutput) {
        Player currentPlayer = firstToMove;
        Board board = new Board();

        while (board.checkGameOver() == Board.state.NONE) {
            if (showOutput) System.out.println("Current Player: " + currentPlayer.getClass().getSimpleName() + " " + currentPlayer.player);
            Point move = currentPlayer.makeMove(board);
            board.setSpaceStatus(move, currentPlayer.player);

            if (showOutput) board.printBoard();
            currentPlayer = switchPlayer(currentPlayer);
        }
        Player winner = switchPlayer(currentPlayer);
        if (showOutput) System.out.println("Game Winner: " + winner.getClass().getSimpleName());
        return winner;
    }

    private Player switchPlayer(Player p) {
        return p == one ? two : one;
    }

    // player ONE moves first
    // creates the board and starts the game
    // this is just a
    public static Player play (Player playerOne, Player playerTwo) {
        return Game.play(playerOne, playerTwo, true);
    }

    public static Player play (Player playerOne, Player playerTwo, boolean showOutput) {
        playerOne.setPlayerName(Board.state.X);
        playerTwo.setPlayerName(Board.state.O);

        Game game = new Game(playerOne, playerTwo);
        return game.play(playerOne, showOutput);
    }

    public static void Tournament(Player playerOne, Player playerTwo, int numberOfGames) {
        int playerOneWins = 0,
            playerTwoWins = 0,
            numberOfGamesPlayed = 0;

        while (numberOfGamesPlayed < numberOfGames) {
            Player winner;
            try {
                winner = Game.play(playerOne, playerTwo, false);
            } catch (Exception ignored) {
                continue;
            }
            if (winner == playerOne)
                playerOneWins++;
            else if (winner == playerTwo)
                playerTwoWins++;

            numberOfGamesPlayed++;
        }

        System.out.println(playerOne.getClass().getSimpleName() + " wins: " + playerOneWins);
        System.out.println(playerTwo.getClass().getSimpleName() + " wins: " + playerTwoWins);
    }
}
