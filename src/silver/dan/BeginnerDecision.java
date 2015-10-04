package silver.dan;

import java.awt.*;

/**
 * Created by dan on 10/3/15.
 */
public class BeginnerDecision extends Player {
    BeginnerDecision(Board board, Board.state player) {
        super(board, player);
    }

    public Point makeMove() {
        // if the player has an open 3-in-a-row
        // return marking the position to get a 4-in-a-row // “Win”

        Point threeInARowOpening = board.findOpenNInARow(3, this.player);
        if (threeInARowOpening != null) {
            System.out.println("win step for " + this.player);
            return threeInARowOpening; //win condition
        }

        Point threeInARowOpeningForEnemy = board.findOpenNInARow(3, this.opponent);
        if (threeInARowOpeningForEnemy != null) {
            System.out.println("blocking " + this.opponent);
            return threeInARowOpeningForEnemy; //block opponent
        }

        //otherwise pick a random empty space
        return board.getRandomOpenSpace();
    }

    //returns the first occurrence of an n length streak in any direction
}
