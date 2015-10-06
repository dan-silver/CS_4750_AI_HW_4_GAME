package silver.dan;

import java.awt.*;

public class Beginner extends Player {
    @Override
    public Point makeMove(Board board) {
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

    //returns the first occurrence of an n length streak in any direction
}
