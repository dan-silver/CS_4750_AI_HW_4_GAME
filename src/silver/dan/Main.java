package silver.dan;

public class Main {

    public static void main(String[] args) {
         BoardTests.runTests();

        // beginner vs human
         Game.play(new Advanced(), new HumanPlayer());

        // beginner vs master for 50 games
         Game.Tournament(new Beginner(), new Master(), 50);



//         Game.Tournament(new Advanced(), new Beginner(), 50);
//        Game.Tournament(new Master(), new Advanced(), 10);
//        Game.Tournament(new Advanced(), new Master(), 10);
    }
}