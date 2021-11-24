package game.board;

import game.GameResult;
import game.Move;
import game.Position;

public interface Board {
    Position getPosition();

    GameResult makeMove(Move move);
}
