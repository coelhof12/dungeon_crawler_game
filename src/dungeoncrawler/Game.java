package dungeoncrawler;

import java.util.Scanner;

public class Game {
    private dungeoncrawler.Player player;
    private char[][] dungeon;

    public Game() {
        player = new dungeoncrawler.Player(1, 1);  // Starting position (x=1, y=1)
        dungeon = new char[10][10];  // 10x10 grid dungeon
        initializeDungeon();
    }

    public void start() {
        System.out.println("Welcome to the Dungeon Crawler!");
        while (true) {
            printDungeon();
            handleInput();
        }
    }

    private void initializeDungeon() {
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                dungeon[i][j] = '.';  // Empty space
            }
        }
        dungeon[5][5] = 'E';  // Enemy position
    }

    private void printDungeon() {
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                if (i == player.getY() && j == player.getX()) {
                    System.out.print('P');  // Player character
                } else {
                    System.out.print(dungeon[i][j]);
                }
            }
            System.out.println();
        }
    }

    private void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Move (w=up, a=left, s=down, d=right): ");
        char input = scanner.next().charAt(0);

        switch (input) {
            case 'w': player.moveUp(); break;
            case 'a': player.moveLeft(); break;
            case 's': player.moveDown(); break;
            case 'd': player.moveRight(); break;
            default: System.out.println("Invalid input!");
        }
    }
}
