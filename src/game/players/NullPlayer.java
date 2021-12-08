package game.players;

import game.Move;
import game.Position;

import java.util.Random;

public class NullPlayer implements Player {


    public NullPlayer() {

    }

    @Override
    public Move makeMove(Position position) {
        return null;

    }
}
