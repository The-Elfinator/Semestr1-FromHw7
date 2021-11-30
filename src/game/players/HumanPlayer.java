package game.players;

import game.Move;
import game.Position;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter your move for " + position.getTurn());
        while (true) {
            String a = in.next();
            String b = in.next();
            if (isInt(a) && isInt(b)) {
                int a1 = Integer.parseInt(a);
                int b1 = Integer.parseInt(b);
                Move move = new Move(position.getTurn(), a1 - 1, b1 - 1);
                if (position.isValid(move)) {
                    return move;
                }
            }
            System.out.println("Sorry, your move is not valid :(");
            System.out.println("Please retry your move   Q('-'Q)");
        }
    }

    private boolean isInt(String s) {
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            flag = flag && Character.isDigit(s.charAt(i));
        }
        return flag;
    }
}
