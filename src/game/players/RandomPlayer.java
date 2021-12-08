package game.players;

import game.Move;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    public RandomPlayer() {

    }

    @Override
    public Move makeMove(Position position) {
        int n = position.getN();
        int m = position.getM();
        //if (position.isValid(move)) {
        return new Move(
                    position.getTurn(),
                    random.nextInt(n),
                    random.nextInt(m)
        );


    }
}
