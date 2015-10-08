package silver.dan;

import java.awt.*;

public class Master extends Advanced {
    // given the current board, return a move (coordinates to place a piece)
    @Override
    public Point makeMove(Board board, boolean showOutput) {
        long startTime = System.nanoTime();
        nodesExpanded = 0;

        MiniMaxNode treeRoot = createMiniMaxTree(board, 4);
        Point move = miniMaxDecision(treeRoot);

        if (showOutput) printMoveStats(System.nanoTime() - startTime);
        return move;
    }
}

