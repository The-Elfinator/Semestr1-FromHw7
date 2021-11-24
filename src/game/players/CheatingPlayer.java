package game.players;

import game.Move;
import game.Position;
//import game.board.TicTacToeBoard; // <--------

public class CheatingPlayer implements Player {

    private final int n, m;

    public CheatingPlayer(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public Move makeMove(Position position) {
        return null;
    }
}
