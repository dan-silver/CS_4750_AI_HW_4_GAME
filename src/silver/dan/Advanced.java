package silver.dan;

import java.awt.*;
import java.util.ArrayList;

public class Advanced extends Player {
    @Override
    public Point makeMove(Board board) {
        MiniMaxNode treeRoot = createMiniMaxTree(board);
        return miniMaxDecision(treeRoot);
    }

    private Point miniMaxDecision(MiniMaxNode treeRoot) {
        MiniMaxNode bestNode = null;
        int maxValue = Integer.MIN_VALUE;
        for (MiniMaxNode node : treeRoot.successors) {
            if ((bestNode == null) || (minValue(node) > maxValue)) {
                bestNode = node;
                maxValue = minValue(node);
            }
        }

        if (bestNode == null)
            return null; //means that there are no possible moves

        return bestNode.move;
    }

    private int maxValue(MiniMaxNode n) {
        if (n.isTerminal())
            return utility(n.board, player, opponent);

        int v = Integer.MIN_VALUE;

        for (MiniMaxNode minNode : n.successors) {
            int maxValue = minValue(minNode);

            if (maxValue > v) {
                v = maxValue;
                n.decisionPath = minNode;
            }
        }

        return v;
    }

    private int minValue(MiniMaxNode n) {
        if (n.isTerminal())
            return utility(n.board, player, opponent);

        int v = Integer.MAX_VALUE;

        for (MiniMaxNode maxNode : n.successors) {
            int minValue = maxValue(maxNode);

            if (minValue < v) {
                v = minValue;
                n.decisionPath = maxNode;
            }
        }

        return v;

    }


    public class MiniMaxNode {
        public MiniMaxNode decisionPath;
        public ArrayList<MiniMaxNode> successors = new ArrayList<>();
        Board board;
        Point move;
        public MiniMaxNode parent;

        public boolean isTerminal() {
            return successors.size() == 0;
        }
    }

    // run the heuristic evaluation function on a board
    private static int utility(Board board, Board.state player, Board.state opponent) {
        return 10 * (board.findNumberOfOpenNInARow(4, player, false) - board.findNumberOfOpenNInARow(4, opponent, false))
                + 3 * (board.findNumberOfOpenNInARow(3, player, true) - board.findNumberOfOpenNInARow(3, opponent, true))
                + (board.findNumberOfOpenNInARow(2, player, true) - board.findNumberOfOpenNInARow(2, opponent, true));
    }

    public MiniMaxNode createMiniMaxTree(Board board) {
        MiniMaxNode root = new MiniMaxNode();
        ArrayList<MiniMaxNode> secondLevel = new ArrayList<>();
//        ArrayList<MiniMaxNode> thirdLevel  = new ArrayList<>();

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
            n.move = move;
        }

        for (MiniMaxNode secondLevelNode : secondLevel) {
            helperBoard = Board.copy(secondLevelNode.board);
            for (int i=0; i<secondLevelNode.board.numberOfEmptySpaces(); i++) {
                MiniMaxNode nodeOnThirdLevel = new MiniMaxNode();
//                thirdLevel.add(nodeOnThirdLevel);
                nodeOnThirdLevel.parent = secondLevelNode;
                secondLevelNode.successors.add(nodeOnThirdLevel);

                //copy the parents board into this node
                nodeOnThirdLevel.board = Board.copy(secondLevelNode.board);

                //use the helperBoard to fill in spaces, so none are duplicated
                Point move = helperBoard.getRandomOpenSpace();
                helperBoard.setSpaceStatus(move, opponent);
                nodeOnThirdLevel.board.setSpaceStatus(move, opponent);
            }
        }
        return root;
    }
}