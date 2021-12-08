package game;

import game.board.TicTacToeBoard;
import game.players.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Давайте сыграем в игру");
        System.out.println("Введите размер поля: ");
        int n, m;
        while (true) {
            try {
                n = Integer.parseInt(scanner.next());
                m = Integer.parseInt(scanner.next());
                if (n >= 1 && m >= 1) {
                    break;
                }
                System.out.println("Wrong input data. n and m should be positive");
            } catch (Exception e) {
                System.out.println("Something's wrong. Please retry your input");
            }
        }
        System.out.println("Введите k: ");
        int k;
        while (true) {
            try {
                k = Integer.parseInt(scanner.next());
                if (k >= 1) {
                    break;
                }
                System.out.println("Wrong input data. k should be positive");
            } catch (Exception e) {
                System.out.println("Something's wrong. Please retry your input");
            }
        }
        int count;
        System.out.println("Сколько будет игроков?");
        while (true) {
            try {
                count = Integer.parseInt(scanner.next());
                if (count >= 2 && count <= 4) {
                    break;
                }
                System.out.println("Sorry but there could be only from 2 to 4 players");
                System.out.println("Please, retry your input");
            } catch (Exception e) {
                System.out.println("Something's wrong. Please retry your input");
            }
        }
        System.out.println("Кто будет играть?");
        System.out.println("1 - рандом, 2 - читер, 3 - пользователь, 4 - последовательный игрок");
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int ind;
            while (true) {
                try {
                    ind = Integer.parseInt(scanner.next());
                    if (1 <= ind && ind <= 4) {
                        break;
                    }
                    System.out.println("WRONG. INPUT. DATA. FOUND. UNKNOWN. PLAYER");
                    System.out.println("PLEASE. RETRY. INPUT");
                } catch (Exception e) {
                    System.out.println("Something's wrong. Please retry your input");
                }
            }
            switch (ind) {
                case 1 -> playerList.add(new RandomPlayer());
                case 2 -> playerList.add(new CheatingPlayer());
                case 3 -> playerList.add(new HumanPlayer(scanner));
                case 4 -> playerList.add(new SequentialPlayer());
                default -> throw new AssertionError("Unknown error");
            }
        }
        System.out.println("Игра началась");
        final int result = new MultiPlayerGame(
                new TicTacToeBoard(n, m, k), playerList
        ).play(true);
        scanner.close();
        switch (result) {
            case 1 -> System.out.println("First player won!");
            case 2 -> System.out.println("Second player won!");
            case 3 -> System.out.println("Third player won!");
            case 4 -> System.out.println("Fourth player won!");
            case 0 -> System.out.println("OMG!!! It's a draw!");
            default -> throw new AssertionError("Unknown result");
        }
    }
}
