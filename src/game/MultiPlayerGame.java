package game;

import game.board.Board;
import game.players.Player;

import java.util.List;

public class MultiPlayerGame {
    private final Board board;
    private final List<Player> playerList;
    private final int countInGame;
    private final int[] playersInGame = new int[4];

    public MultiPlayerGame(Board board, List<Player> players) {
        this.board = board;
        this.playerList = players;
        this.countInGame = players.size();
        for (int i = 0; i < players.size(); i++) {
            this.playersInGame[i] = 1;
        }
    }

    public int play(boolean log) {
        while (true) {
            int ind = 0;
            for (Player player : playerList) {
                if (playersInGame[ind] != 0) {
                    int result;
                    try {
                        result = makeMove(player, ind + 1, log);
                    } catch (Exception e) {
                        result = 10;
                    }
                    if (result == 10) {
                        System.out.printf("Player number %s is lose", ind + 1);
                        System.out.println();
                        playersInGame[ind] = 0;
                        int indInGame = -1;
                        int curr = 0;
                        for (int j = 0; j < 4; j++) {
                            if (playersInGame[j] != 0) {
                                curr++;
                                if (indInGame != -1) {
                                    break;
                                }
                                indInGame = j;
                            }
                        }
                        if (curr == 1) {
                            return indInGame + 1;
                        }
                        board.getPosition().setTurn(switchTurn());
                    }
                    else if (result != -1) {
                        return result;
                    }
                } else {
                    board.getPosition().setTurn(switchTurn());
                }
                ind++;
                while (ind >= countInGame && ind < 4) {
                    board.getPosition().setTurn(switchTurn());
                    ind++;
                }

            }
        }
    }

    private Cell switchTurn() {
        Cell x = board.getPosition().getTurn();
        switch (x) {
            case X -> x = Cell.O;
            case O -> x = Cell.MINUS;
            case MINUS -> x = Cell.SLASH;
            case SLASH -> x = Cell.X;
        }
        return x;
    }


    private int makeMove(Player player, int num, boolean log) {
        Move move;
        try {
            move = player.makeMove(board.getPosition());
            if (move == null) {
                return 10;
            }
        } catch (Exception e) {
            return 10;
        }
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player " + num);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        return switch (result) {
            case WIN -> num;
            case LOSE -> 10;
            case DRAW -> 0;
            case UNKNOWN -> -1;
        };
    }
}
