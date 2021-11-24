package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Давайте сыграем в игру");
        System.out.println("Введите размер поля: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Введите k: ");
        int k = scanner.nextInt();
        System.out.println("Игра началась");
        final int result = new TwoPlayerGame(
                new TicTacToeBoard(n, m, k),
                new RandomPlayer(n, m),
                new HumanPlayer(scanner)
        ).play(false);
        scanner.close();
        switch (result) {
            case 1 -> System.out.println("First player won!");
            case 2 -> System.out.println("Second player won!");
            case 0 -> System.out.println("Draw!");
            default -> throw new AssertionError("Unknown result");
        }
    }
}
