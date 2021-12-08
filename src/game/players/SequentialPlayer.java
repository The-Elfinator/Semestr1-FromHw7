package game.players;

import game.Move;
import game.Position;

public class SequentialPlayer implements Player {

    public SequentialPlayer() {

    }

    @Override
    public Move makeMove(Position position) {
        int n = position.getN();
        int m = position.getM();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(position.getTurn(), r, c);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
