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

        //main game loop
        // loop until the game is either a win or tie
        while (board.checkGameOver() == Board.state.NONE) {
            //check for tie condition
            if (board.numberOfEmptySpaces() == 0)
                return null; //means there was a tie

            if (showOutput) System.out.println("Current Player: " + currentPlayer.getClass().getSimpleName() + " " + currentPlayer.player);
            Point move = currentPlayer.makeMove(board, showOutput);
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
    // single game between two players and shows the board on each turn
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
            tieCount = 0;

        for (int i=0; i<numberOfGames; i++) {
            Player winner = Game.play(playerOne, playerTwo, false);
            if (winner == playerOne)
                playerOneWins++;
            else if (winner == playerTwo)
                playerTwoWins++;
            else
                tieCount++;

            System.out.print(".");
        }

        System.out.println(playerOne.getClass().getSimpleName() + " wins: " + Math.round(((float) playerOneWins/numberOfGames)*100) + "%");
        System.out.println(playerTwo.getClass().getSimpleName() + " wins: " + Math.round(((float)playerTwoWins/numberOfGames)*100) + "%");
        System.out.println("Ties: " + Math.round(((float) tieCount/numberOfGames)*100) + "%");
    }
}
