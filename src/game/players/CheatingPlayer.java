package game.players;

import game.Move;
import game.Position;
import game.board.TicTacToeBoard; // <--------

public class CheatingPlayer implements Player {

    private final int n, m;

    public CheatingPlayer(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public Move makeMove(Position position) {
        final TicTacToeBoard board = (TicTacToeBoard) position;
        Move first = null;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                final Move move = new Move(position.getTurn(), r, c);
                if (position.isValid(move)) {
                    if (first == null) {
                        first = move;
                    } else {
                        board.makeMove(move);
                    }
                }
            }
        }
        return first;
    }
}
