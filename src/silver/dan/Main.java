package silver.dan;

public class Main {

    public static void main(String[] args) {
        BoardTests.runTests();

        Board board = new Board();

        BeginnerDecision beginner1 = new BeginnerDecision(board, Board.state.O);
        BeginnerDecision beginner2 = new BeginnerDecision(board, Board.state.X);

        Game game = new Game(beginner1, beginner2);
        game.play();
    }
}
