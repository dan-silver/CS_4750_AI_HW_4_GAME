package silver.dan;

import java.awt.*;
import java.util.ArrayList;

public class Advanced extends Player {
    int nodesExpanded;

    // given the current board, return a move (coordinates to place a piece)
    @Override
    public Point makeMove(Board board, boolean showOutput) {
        long startTime = System.nanoTime();
        nodesExpanded = 0;

        MiniMaxNode treeRoot = createMiniMaxTree(board, 2); // 2ply MiniMax game tree
        Point move = miniMaxDecision(treeRoot);

        if (showOutput) printMoveStats(System.nanoTime() - startTime);
        return move;
    }

    protected void printMoveStats(long elapsedTime) {
        System.out.println("Time: " + elapsedTime/1000000 + "ms (" + elapsedTime/1000000/1000 + "s)"); //convert ns to ms
        System.out.println("Nodes expanded: " + nodesExpanded);
    }


    protected Point miniMaxDecision(MiniMaxNode treeRoot) {
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
        nodesExpanded++;
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
        nodesExpanded++;
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
        public boolean isTerminal = false;

        public boolean isTerminal() {
            return successors.size() == 0;
        }
    }

    // run the heuristic evaluation function on a board
    private static int utility(Board board, Board.state player, Board.state opponent) {
        return 10 * (board.findNSpacesInARow(4, player, false) - board.findNSpacesInARow(4, opponent, false))
                + 3 * (board.findNSpacesInARow(3, player, true) - board.findNSpacesInARow(3, opponent, true))
                + (board.findNSpacesInARow(2, player, true) - board.findNSpacesInARow(2, opponent, true));
    }

    public MiniMaxNode createMiniMaxTree(Board board, int numberOfLevels) {
        MiniMaxNode root = new MiniMaxNode();
        ArrayList<MiniMaxNode> topLevel = new ArrayList<>();
        topLevel.add(root);

        root.board = Board.copy(board);

        // this is a helper that's re-written to with each new level, and passed to the next level to create new nodes
        ArrayList<MiniMaxNode> previousLevel = topLevel;
        for (int i=0; i<numberOfLevels; i++)
            previousLevel = addLevelToTree(previousLevel, i % 2 == 0 ? player : opponent); // levels alternate types

        return root;
    }

    // Adds another layer to the MiniMax tree.  The previous layers nodes and the type of level are pass in as parameters
    public ArrayList<MiniMaxNode> addLevelToTree(ArrayList<MiniMaxNode> previousLevel, Board.state player) {
        ArrayList<MiniMaxNode> levelNodes = new ArrayList<>();
        for (MiniMaxNode previousLevelNode : previousLevel) {
            if (previousLevelNode.isTerminal) continue; // don't add successors to terminal nodes
            Board helperBoard = Board.copy(previousLevelNode.board);
            // for every open space, there is a possible action the player can take
            for (int i=0; i<previousLevelNode.board.numberOfEmptySpaces(); i++) {

                MiniMaxNode newNode = new MiniMaxNode();
                previousLevelNode.successors.add(newNode);

                //use the helperBoard to fill in spaces, so none are duplicated
                Point move = helperBoard.getRandomOpenSpace();
                helperBoard.setSpaceStatus(move, player);

                levelNodes.add(newNode);

                //copy the parents board into this node
                newNode.board = Board.copy(previousLevelNode.board);
                newNode.board.setSpaceStatus(move, player);
                newNode.move = move;


                if (newNode.board.checkGameOver() != Board.state.NONE)
                    newNode.isTerminal = true;

            }
        }
        return levelNodes;
    }
}