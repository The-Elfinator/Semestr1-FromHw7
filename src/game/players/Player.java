package game.players;

import game.Move;
import game.Position;

public interface Player {
    Move makeMove(Position position);
}
