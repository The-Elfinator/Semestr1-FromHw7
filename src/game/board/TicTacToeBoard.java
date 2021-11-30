package game.board;

import game.Cell;
import game.GameResult;
import game.Move;
import game.Position;

import java.util.Arrays;
import java.util.Map;

class TicTacToeBoard implements Board, Position {

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "O",
            Cell.MINUS, "-",
            Cell.SLASH, "|"
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

        switch (turn) {
            case X -> turn = Cell.O;
            case O -> turn = Cell.MINUS;
            case MINUS -> turn = Cell.SLASH;
            case SLASH -> turn = Cell.X;
        }

        return GameResult.UNKNOWN;
    }

    private boolean checkWin() {
        int count = 1;
        if (count == k) {
            return true;
        }
        int posX, posY;
        int[][] vectors = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int t = 0; t < 8; t += 2) {
            posX = this.x + vectors[t][0];
            posY = this.y + vectors[t][1];
            //System.out.println(posX + " " + posY);
            while (0 <= posX && posX < n && 0 <= posY && posY < m) {
                if (field[posX][posY].equals(value)) {
                    count++;
                } else {
                    break;
                }
                posX += vectors[t][0];
                posY += vectors[t][1];
//                System.out.println(count);
            }
            posX = this.x + vectors[t + 1][0];
            posY = this.y + vectors[t + 1][1];
//            System.out.println(posX + " " + posY);
            while (0 <= posX && posX < n && 0 <= posY && posY < m) {
                if (field[posX][posY].equals(value)) {
                    count++;
                } else {
                    break;
                }
                posX += vectors[t + 1][0];
                posY += vectors[t + 1][1];
//                System.out.println(count);
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
    public Cell getTurn() {
        return turn;
    }

    @Override
    public void setTurn(Cell cell) {
        this.turn = cell;
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
