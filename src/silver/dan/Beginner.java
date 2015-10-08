package silver.dan;

import java.awt.*;

public class Beginner extends Player {

    // given the current board, return a move (coordinates to place a piece)
    @Override
    public Point makeMove(Board board, boolean showOutput) {
        // if the player has an open 3-in-a-row
        // return marking the position to get a 4-in-a-row // “Win”

        Point threeInARowOpening = board.findOpenNInARow(3, this.player);
        if (threeInARowOpening != null) {
            return threeInARowOpening; //win condition
        }

        Point threeInARowOpeningForEnemy = board.findOpenNInARow(3, this.opponent);
        if (threeInARowOpeningForEnemy != null) {
            return threeInARowOpeningForEnemy; //block opponent
        }

        //otherwise pick a random empty space
        return board.getRandomOpenSpace();
    }
}

