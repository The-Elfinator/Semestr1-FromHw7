package game;

public class SequentialPlayer implements Player {

    private final int n, m;

    public SequentialPlayer(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public Move makeMove(Position position) {
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
