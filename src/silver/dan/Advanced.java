package silver.dan;

import java.awt.*;
import java.util.ArrayList;

public class Advanced extends Player {
    enum nodeType {MIN, MAX};

    @Override
    public Point makeMove(Board board) {
        return null;
    }

    public class MiniMaxNode {
        public int value;
        public ArrayList<MiniMaxNode> successors = new ArrayList<>();
        Board board;
        public MiniMaxNode parent;

        public boolean isTerminal() {
            return successors.size() == 0;
        }
    }

    public void createMiniMaxTree(Board board) {
        MiniMaxNode root = new MiniMaxNode();
        ArrayList<MiniMaxNode> secondLevel = new ArrayList<>();
        ArrayList<MiniMaxNode> thirdLevel  = new ArrayList<>();

        // for every open space, there is a possible action the player can take
        Board helperBoard = Board.copy(board);
        for (int i=0;i<board.numberOfEmptySpaces();i++) {
            MiniMaxNode n = new MiniMaxNode();
            root.successors.add(n);
            n.parent = root;
            secondLevel.add(n);


            //use the helperBoard to fill in spaces, so none are duplicated
            Point move = helperBoard.getRandomOpenSpace();
            helperBoard.setSpaceStatus(move, player);
            n.board = Board.copy(board);
            n.board.setSpaceStatus(move, player);
        }

        for (MiniMaxNode secondLevelNode : secondLevel) {
            helperBoard = Board.copy(secondLevelNode.board);
            for (int i=0;i<secondLevelNode.board.numberOfEmptySpaces();i++) {
                MiniMaxNode nodeOnThirdLevel = new MiniMaxNode();
                thirdLevel.add(nodeOnThirdLevel);
                nodeOnThirdLevel.parent = secondLevelNode;
                secondLevelNode.successors.add(nodeOnThirdLevel);


                //copy the parents board into this node
                nodeOnThirdLevel.board = Board.copy(secondLevelNode.board);

                //give the opponent this board to get their action



                //use the helperBoard to fill in spaces, so none are duplicated
                Point move = helperBoard.getRandomOpenSpace();
                helperBoard.setSpaceStatus(move, opponent);
                nodeOnThirdLevel.board.setSpaceStatus(move, opponent);
            }
        }
    }
}