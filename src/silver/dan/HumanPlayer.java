package silver.dan;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by dan on 10/4/15.
 */
public class HumanPlayer extends Player{
    Scanner input = new Scanner(System.in);

    @Override
    public Point makeMove() {
        System.out.println("Enter move(row space col): ");
        int row = input.nextInt();
        int col = input.nextInt();
        return new Point(row, col);
    }
}
