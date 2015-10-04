package silver.dan;

public class Main {

    public static void main(String[] args) {
//        BoardTests.runTests();

        // beginner vs human
        Game.play(new BeginnerDecision(), new HumanPlayer());

    }
}
