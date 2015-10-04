package silver.dan;

import java.awt.*;

public class BoardTests {
    public static void runTests() {
        BoardTests bt = new BoardTests();
        System.out.println("Test 1: " + bt.case1());
        System.out.println("Test 2: " + bt.case2());
        System.out.println("Test 3: " + bt.case3());
        System.out.println("Test 4: " + bt.case4());
        System.out.println("Test 5: " + bt.case5());
        System.out.println("Test 6: " + bt.case6());
        System.out.println("Test 7: " + bt.case7());
        System.out.println("Test 8: " + bt.case8());
        System.out.println("Test 9: " + bt.case9());
        System.out.println("Test 10: " + bt.case10());
        System.out.println("Test 11: " + bt.case11());
        System.out.println("Test 12: " + bt.case12());
        System.out.println("Test 13: " + bt.case13());
        System.out.println("Test 14: " + bt.case14());
        System.out.println("Test 15: " + bt.case15());
        System.out.println("Test 16: " + bt.case16());
        System.out.println("Test 17: " + bt.case17());
    }

    public boolean case1() {
        Board board = new Board();

        board.setSpaceStatus(2,0, Board.state.O);
        board.setSpaceStatus(2,1, Board.state.NONE);
        board.setSpaceStatus(2,2, Board.state.X);
        board.setSpaceStatus(2,3, Board.state.X);
        board.setSpaceStatus(2,4, Board.state.X);

        return board.checkGameOver() == Board.state.NONE;
    }

    public boolean case2() {
        Board board = new Board();

        board.setSpaceStatus(2,0, Board.state.O);
        board.setSpaceStatus(2,1, Board.state.X);
        board.setSpaceStatus(2,2, Board.state.X);
        board.setSpaceStatus(2,3, Board.state.X);
        board.setSpaceStatus(2,4, Board.state.X);

        return board.checkGameOver() == Board.state.X;
    }

    public boolean case3() {
        Board board = new Board();

        board.setSpaceStatus(2,0, Board.state.O);
        board.setSpaceStatus(2,1, Board.state.O);
        board.setSpaceStatus(2,2, Board.state.O);
        board.setSpaceStatus(2,3, Board.state.O);
        board.setSpaceStatus(2,4, Board.state.X);
        return board.checkGameOver() == Board.state.O;
    }

    public boolean case4() {
        Board board = new Board();

        board.setSpaceStatus(2,0, Board.state.NONE);
        board.setSpaceStatus(2,1, Board.state.O);
        board.setSpaceStatus(2,2, Board.state.O);
        board.setSpaceStatus(2,3, Board.state.O);
        board.setSpaceStatus(2,4, Board.state.X);

        return board.checkGameOver() == Board.state.NONE;
    }


    public boolean case5() {
        Board board = new Board();

        board.setSpaceStatus(0,4, Board.state.NONE);
        board.setSpaceStatus(1,4, Board.state.O);
        board.setSpaceStatus(2,4, Board.state.O);
        board.setSpaceStatus(3,4, Board.state.O);
        board.setSpaceStatus(4,4, Board.state.X);

        return board.checkGameOver() == Board.state.NONE;
    }

    public boolean case6() {
        Board board = new Board();

        board.setSpaceStatus(0,4, Board.state.NONE);
        board.setSpaceStatus(1,4, Board.state.O);
        board.setSpaceStatus(2,4, Board.state.O);
        board.setSpaceStatus(3,4, Board.state.O);
        board.setSpaceStatus(4,4, Board.state.O);

        return board.checkGameOver() == Board.state.O;
    }


    public boolean case7() {
        Board board = new Board();

        board.setSpaceStatus(0,4, Board.state.X);
        board.setSpaceStatus(1,4, Board.state.X);
        board.setSpaceStatus(2,4, Board.state.X);
        board.setSpaceStatus(3,4, Board.state.X);
        board.setSpaceStatus(4,4, Board.state.O);

        return board.checkGameOver() == Board.state.X;
    }


    public boolean case8() {
        Board board = new Board();

        board.setSpaceStatus(0,4, Board.state.X);
        board.setSpaceStatus(1,4, Board.state.X);
        board.setSpaceStatus(2,4, Board.state.NONE);
        board.setSpaceStatus(3,4, Board.state.X);
        board.setSpaceStatus(4,4, Board.state.O);

        return board.checkGameOver() == Board.state.NONE;
    }

    public boolean case9() {
        Board board = new Board();

        board.setSpaceStatus(0,0, Board.state.X);
        board.setSpaceStatus(1,1, Board.state.X);
        board.setSpaceStatus(2,2, Board.state.NONE);
        board.setSpaceStatus(3,3, Board.state.X);
        board.setSpaceStatus(4,4, Board.state.O);

        return board.checkGameOver() == Board.state.NONE;
    }

    public boolean case10() {
        Board board = new Board();

        board.setSpaceStatus(0,0, Board.state.NONE);
        board.setSpaceStatus(1,1, Board.state.X);
        board.setSpaceStatus(2,2, Board.state.X);
        board.setSpaceStatus(3,3, Board.state.X);
        board.setSpaceStatus(4,4, Board.state.X);

        return board.checkGameOver() == Board.state.X;
    }


    public boolean case11() {
        Board board = new Board();

        board.setSpaceStatus(0,1, Board.state.X);
        board.setSpaceStatus(1,2, Board.state.X);
        board.setSpaceStatus(2,3, Board.state.X);
        board.setSpaceStatus(3,4, Board.state.X);

        return board.checkGameOver() == Board.state.X;
    }

    public boolean case12() {
        Board board = new Board();

        board.setSpaceStatus(0,1, Board.state.X);
        board.setSpaceStatus(1,2, Board.state.X);
        board.setSpaceStatus(2,3, Board.state.X);
        board.setSpaceStatus(3,4, Board.state.O);

        return board.checkGameOver() == Board.state.NONE;
    }

    public boolean case13() {
        Board board = new Board();

        board.setSpaceStatus(1,4, Board.state.X);
        board.setSpaceStatus(2,3, Board.state.X);
        board.setSpaceStatus(3,2, Board.state.X);
        board.setSpaceStatus(4,1, Board.state.X);

        return board.checkGameOver() == Board.state.X;
    }

    public boolean case14() {
        Board board = new Board();

        board.setSpaceStatus(1,4, Board.state.X);
        board.setSpaceStatus(2,3, Board.state.X);
        board.setSpaceStatus(3,2, Board.state.X);
        board.setSpaceStatus(4,1, Board.state.O);

        return board.checkGameOver() == Board.state.NONE;
    }

    public boolean case15() {
        Board board = new Board();

        board.setSpaceStatus(1, 1, Board.state.X);
        board.setSpaceStatus(1, 2, Board.state.X);
        board.setSpaceStatus(1, 3, Board.state.X);

        Point space = board.findOpenNInARow(3, Board.state.X);
        return (space.x == 1 && space.y == 0) || (space.x == 1 && space.y == 4);
    }

    public boolean case16() {
        Board board = new Board();

        board.setSpaceStatus(2, 3, Board.state.X);
        board.setSpaceStatus(3, 3, Board.state.X);
        board.setSpaceStatus(4, 3, Board.state.X);

        Point space = board.findOpenNInARow(3, Board.state.X);
        return (space.x == 1 && space.y == 3);
    }

    public boolean case17() {
        Board board = new Board();

        board.setSpaceStatus(2, 2, Board.state.X);
        board.setSpaceStatus(3, 3, Board.state.X);
        board.setSpaceStatus(4, 4, Board.state.X);

        Point space = board.findOpenNInARow(3, Board.state.X);
        return (space.x == 1 && space.y == 1);
    }


}