package game.players;

import game.Move;
import game.Position;

public class ExceptionPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        throw new ArithmeticException();
    }
}
