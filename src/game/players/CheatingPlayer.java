package game.players;

import game.Move;
import game.Position;
//import game.board.TicTacToeBoard; // <--------

public class CheatingPlayer implements Player {

    public CheatingPlayer() {

    }

    @Override
    public Move makeMove(Position position) {
        int n = position.getN();
        int m = position.getM();
        System.out.println("My master told me, that if you call me, I will become anti-sequential player");
        for (int r = n -1; r >= 0; r--) {
            for (int c = m -1; c >= 0; c--) {
                final Move move = new Move(position.getTurn(), r, c);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
