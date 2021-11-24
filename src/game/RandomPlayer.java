package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    private final int n, m;

    public RandomPlayer(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public Move makeMove(Position position) {
        Move move = new Move(
                    position.getTurn(),
                    random.nextInt(n),
                    random.nextInt(m)
        );
        //if (position.isValid(move)) {
        return move;


    }
}
