package game;

import game.board.Board;

public interface Position extends Board {
    Cell getTurn();

    void setTurn(Cell cell);

    boolean isValid(Move move);

    int getN();
    int getM();

    //Cell getCell(int row, int column);
}
