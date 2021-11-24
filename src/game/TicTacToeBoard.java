package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "O"
    );

    private final Cell[][] field;
    private Cell turn;
    private final int n, m, k;
    private int x, y;
    private int countOfTurns;
    private Cell value;

    public TicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.countOfTurns = 0;
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }



    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        countOfTurns++;
        this.x = move.getRow();
        this.y = move.getCol();
        this.value = move.getValue();
        if (!isValid(move)) {
            return GameResult.LOSE;
        }
        field[x][y] = value;
        if (checkWin()) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;

        return GameResult.UNKNOWN;
    }

    private boolean checkWin() {
        int count = 1;
        if (count == k) {
            return true;
        }
        int x = this.x;
        int y = this.y;
        int[][] vectors = {{1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int t = 0; t < 6; t += 2) {
            x += vectors[t][0];
            y += vectors[t][1];
            while (0 <= x && x < n && 0 <= y && y < m) {
                if (field[x][y] == value) {
                    count++;
                } else {
                    break;
                }
                x += vectors[t][0];
                y += vectors[t][1];
            }
            x = this.x + vectors[t + 1][0];
            y = this.y + vectors[t + 1][1];
            while (0 <= x && x < n && 0 <= y && y < m) {
                if (field[x][y] == value) {
                    count++;
                } else {
                    break;
                }
                x += vectors[t + 1][0];
                y += vectors[t + 1][1];
            }
            if (count == k) {
                return true;
            }
            count = 1;
        }
        return false;
    }

    private boolean checkDraw() {
        return countOfTurns == n * m;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public Cell getTurn() {
        return turn;
    }



    @Override
    public String toString() {
        StringBuilder tab = new StringBuilder();
        for (int k = 0; k < (int) (Math.ceil(Math.log10(Math.max(n, m))) + 1); k++) {
            tab.append(" ");
        }
        final StringBuilder sb = new StringBuilder(" ");
        for (int c = 0; c < m; c++) {
            sb.append(tab.substring((int)(Math.log10(c + 1)))).append(c+1);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < n; r++) {
            sb.append(r + 1).append(tab.substring((int)(Math.log10(r + 1))));
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell)).append(tab);
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
