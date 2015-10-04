package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public state spaces[][];
    enum state {NONE, X, O};

    Board() {
        spaces = new state[5][5];
        setAllSpaces(state.NONE);
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

    public ArrayList<Point> findNInARow(int n, Board.state player) {
        //brute force
        //loop rows, columns and search neighbors looking for a streak
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
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

                lookInDirection:
                for (Point direction : directionsToSearch) {
                    ArrayList<Point> pointsInStreak = new ArrayList<>();
                    Point position = new Point(i, j);

                    for (int x = 0; x < n; x ++) {
                        Board.state currentSpace;
                        try {
                            currentSpace = spaces[position.x][position.y];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue lookInDirection;
                        }

                        if (currentSpace != player)
                            continue lookInDirection;

                        position.x += direction.x;
                        position.y += direction.y;
                        pointsInStreak.add(new Point(position.x, position.y));
                    }
                    return pointsInStreak;
                }
            }
        }

        return null;
    }


    public Point findOpenNInARow(int n, Board.state player) {
        //brute force
        //loop rows, columns and search neighbors looking for a streak
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
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

                Point directionLooking;
                lookInDirection:
                for (Point direction : directionsToSearch) {
                    directionLooking = direction;
                    ArrayList<Point> pointsInStreak = new ArrayList<>();
                    Point position = new Point(i, j);

                    for (int x = 0; x < n; x ++) {
                        Board.state currentSpace;
                        try {
                            currentSpace = spaces[position.x][position.y];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue lookInDirection;
                        }

                        if (currentSpace != player)
                            continue lookInDirection;

                        position.x += direction.x;
                        position.y += direction.y;
                        pointsInStreak.add(new Point(position.x, position.y));
                    }

                    // check if either end of the streak is open
                    // if so, return it

                    Point startOfStreak = pointsInStreak.get(0);
                    Point endOfStreak = pointsInStreak.get(pointsInStreak.size() - 1);

                    Point possibleOpeningBeforeStreak = new Point();
                    possibleOpeningBeforeStreak.x = startOfStreak.x - directionLooking.x;
                    possibleOpeningBeforeStreak.y = startOfStreak.y - directionLooking.y;

                    Point possibleOpeningAfterStreak = new Point();
                    possibleOpeningBeforeStreak.x = endOfStreak.x + directionLooking.x;
                    possibleOpeningBeforeStreak.y = endOfStreak.y + directionLooking.y;

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
