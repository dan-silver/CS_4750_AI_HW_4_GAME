package silver.dan;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public state spaces[][];
    enum state {NONE, X, O};

    //used in board brute force searches
    Point directionsToSearch[] = new Point[]{
            new Point(-1,0), //look up
            new Point(-1,1), //look diagonal upper right
            new Point(0, 1), //look right
            new Point(1, 1), //look diagonal lower right
            new Point(1, 0), //look down
            new Point(1, -1), //look diagonal lower left
            new Point(0, -1), //look left
            new Point(-1, -1), //look diagonal upper left
    };


    Board() {
        spaces = new state[5][5];
        setAllSpaces(state.NONE);
    }

    public static Board copy(Board source) {
        Board newBoard = new Board();
        for (int i=0; i<5; i++) {
            System.arraycopy(source.spaces[i], 0, newBoard.spaces[i], 0, 5);
        }
        return newBoard;
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < 5; i++) {
            sb.append("\n | --------------------- | ");
            sb.append("\n | ");
            for (int j=0; j<5; j++) {

                if (spaces[i][j] == state.NONE)
                    sb.append("  |  ");
                else
                    sb.append(spaces[i][j]).append(" |  ");
            }
        }
        sb.append("\n | --------------------- | ");
        System.out.println(sb.toString());

    }

    public Point getRandomOpenSpace() {
        while (true) {
            int row = ThreadLocalRandom.current().nextInt(0, 5);
            int column = ThreadLocalRandom.current().nextInt(0, 5);
            if (spaces[row][column] == state.NONE)
                return new Point(row, column);
        }
    }

    public void setSpaceStatus(Point p, state player) {
        this.setSpaceStatus(p.x, p.y, player);
    }

    public void setAllSpaces(state state) {
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                spaces[i][j] = state;
    }

    public void setSpaceStatus(int row, int col, state status) {
        spaces[row][col] = status;
    }


    public int numberOfSpaceType(state state) {
        int count = 0;
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                if (spaces[i][j] == state)
                    count++;
        return count;
    }

    public int numberOfEmptySpaces() {
        return numberOfSpaceType(state.NONE);
    }

    public int findNumberOfOpenNInARow(int n, Board.state player) {
        //brute force
        int foundCount = 0;
        //store all found streaks of n markers in an array to prevent duplicates
        // string format: "START X, START Y, DIRECTION"
        ArrayList<String> foundStreaks = new ArrayList<>();

        //loop rows, columns and search neighbors looking for a streak
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Point directionLooking;
                lookInDirection:
                for (Point direction : directionsToSearch) {
                    directionLooking = direction;
                    ArrayList<Point> pointsInStreak = new ArrayList<>();
                    Point position = new Point(i, j);

                    for (int x = 0; x < n; x++) {
                        Board.state currentSpace;
                        pointsInStreak.add(new Point(position.x, position.y));
                        try {
                            currentSpace = spaces[position.x][position.y];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue lookInDirection;
                        }

                        if (currentSpace != player) {
                            continue lookInDirection;
                        }

                        position.x += direction.x;
                        position.y += direction.y;
                    }

                    // check if either end of the streak is open
                    // if so, return it

                    Point startOfStreak = pointsInStreak.get(0);
                    Point lastPointInStreak = pointsInStreak.get(n-1);

                    Point possibleOpeningBeforeStreak = new Point();
                    possibleOpeningBeforeStreak.x = startOfStreak.x - directionLooking.x;
                    possibleOpeningBeforeStreak.y = startOfStreak.y - directionLooking.y;

                    Point spaceAfterStreak = new Point();
                    spaceAfterStreak.x = lastPointInStreak.x + direction.x;
                    spaceAfterStreak.y = lastPointInStreak.y + direction.y;

                    boolean validStreak = false;
                    try {
                        if (spaces[possibleOpeningBeforeStreak.x][possibleOpeningBeforeStreak.y] == state.NONE)
                            validStreak = true;

                        // space after streak cannot be of the same type. that would be a duplicate since the larger streak is counted

                        if (spaces[spaceAfterStreak.x][spaceAfterStreak.y] == player)
                            validStreak = false;

                    } catch (ArrayIndexOutOfBoundsException ignored) {}

                    //check if it's already been found
                    if (validStreak) {
                        String uniqueStreakString = startOfStreak.x + "," + startOfStreak.y + "," + pointsInStreak.get(n-1).x + "," + pointsInStreak.get(n-1).y,
                               uniqueStreakStringReversed = pointsInStreak.get(n-1).x + "," + pointsInStreak.get(n-1).y + "," + startOfStreak.x + "," + startOfStreak.y;


                        for (String streak : foundStreaks) {
                            if (streak.equals(uniqueStreakString)) {
                                validStreak = false;
                                break;
                            }
                        }

                        if (validStreak) {
                            foundStreaks.add(uniqueStreakString);
                            foundStreaks.add(uniqueStreakStringReversed);
                            foundCount++;
                        }
                    }
                }
            }

        }
        return foundCount;
    }


    public Point findOpenNInARow(int n, Board.state player) {
        //brute force
        //loop rows, columns and search neighbors looking for a streak
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                Point directionLooking;
                lookInDirection:
                for (Point direction : directionsToSearch) {
                    directionLooking = direction;
                    ArrayList<Point> pointsInStreak = new ArrayList<>();
                    Point position = new Point(i, j);

                    for (int x = 0; x < n; x ++) {
                        Board.state currentSpace;
                        pointsInStreak.add(new Point(position.x, position.y));
                        try {
                            currentSpace = spaces[position.x][position.y];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue lookInDirection;
                        }

                        if (currentSpace != player) {
                            continue lookInDirection;
                        }

                        position.x += direction.x;
                        position.y += direction.y;
                    }

                    // check if either end of the streak is open
                    // if so, return it

                    Point startOfStreak = pointsInStreak.get(0);
                    Point endOfStreak = pointsInStreak.get(pointsInStreak.size() - 1);

                    Point possibleOpeningBeforeStreak = new Point();
                    possibleOpeningBeforeStreak.x = startOfStreak.x - directionLooking.x;
                    possibleOpeningBeforeStreak.y = startOfStreak.y - directionLooking.y;

                    Point possibleOpeningAfterStreak = new Point();
                    possibleOpeningAfterStreak.x = endOfStreak.x + directionLooking.x;
                    possibleOpeningAfterStreak.y = endOfStreak.y + directionLooking.y;

                    try {
                        if (spaces[possibleOpeningBeforeStreak.x][possibleOpeningBeforeStreak.y] == state.NONE)
                            return new Point(possibleOpeningBeforeStreak.x, possibleOpeningBeforeStreak.y);
                        if (spaces[possibleOpeningAfterStreak.x][possibleOpeningAfterStreak.y] == state.NONE)
                            return new Point(possibleOpeningAfterStreak.x, possibleOpeningAfterStreak.y);
                    } catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }

        return null;
    }


    //returns true if the game is over
    public state checkGameOver() {

        //loop streak on rows
        for (int i=0; i<5; i++) { //loop rows
            for (int offset = 0; offset < 2; offset++) { //two possible positions in the row for victory
                state streak = spaces[i][offset];
                boolean streakFinished = true;
                for (int j=offset+1; j<4+offset; j++) {
                    if (spaces[i][j] != streak || streak == state.NONE) {
                        streakFinished = false;
                        break;
                    }
                }
                if (streakFinished && streak != state.NONE) {
                    return streak;
                }
            }
        }

        //check streak on columns
        for (int i=0; i<5; i++) { //loop columns
            for (int offset = 0; offset < 2; offset++) { //two possible positions in the row for victory
                state streak = spaces[offset][i];
                boolean streakFinished = true;
                for (int j=offset+1; j<4+offset; j++) {
                    if (spaces[j][i] != streak || streak == state.NONE) {
                        streakFinished = false;
                        break;
                    }
                }
                if (streakFinished && streak != state.NONE) {
                    return streak;
                }
            }
        }


        Point[] startingPoints = new Point[]{
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 1),
        };


        //check streak on diagonals \
        for (Point p : startingPoints) {
            int startRow = p.x;
            int startCol = p.y;
            boolean streakFinished = true;
            state streak = spaces[startRow][startCol];
            for (int i=0; i<4; i++) {
                if (spaces[startRow + i][startCol + i] != streak || streak == state.NONE) {
                    streakFinished = false;
                    break;
                }
            }
            if (streakFinished) {
                return streak;
            }
        }

        startingPoints = new Point[]{
                new Point(0, 3),
                new Point(0, 4),
                new Point(1, 3),
                new Point(1, 4),
        };

        //check streak on diagonals \
        for (Point p : startingPoints) {
            int startRow = p.x;
            int startCol = p.y;
            boolean streakFinished = true;
            state streak = spaces[startRow][startCol];
            for (int i=0; i<4; i++) {
                if (spaces[startRow + i][startCol - i] != streak || streak == state.NONE) {
                    streakFinished = false;
                    break;
                }
            }
            if (streakFinished) {
                return streak;
            }
        }

        return state.NONE;
    }
}
