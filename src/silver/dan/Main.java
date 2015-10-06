package silver.dan;

public class Main {

    public static void main(String[] args) {
         BoardTests.runTests();

        // beginner vs human
//         Game.play(new Beginner(), new HumanPlayer());

        // beginner vs advanced
        // Game.Tournament(new Beginner(), new Advanced(), 100);
        Game.Tournament(new Beginner(), new Advanced(), 50);
    }
}
