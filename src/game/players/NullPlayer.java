package game.players;

import game.Move;
import game.Position;

import java.util.Random;

public class NullPlayer implements Player {

    private final int n, m;

    public NullPlayer(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public Move makeMove(Position position) {
        return null;

    }
}
