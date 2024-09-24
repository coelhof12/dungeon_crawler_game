package dungeoncrawler;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private char[][] dungeon;
    private final int WIDTH = 20;  // Width of the dungeon
    private final int HEIGHT = 10; // Height of the dungeon
    private Random rand = new Random();

    public Game() {
        player = new Player(1, 1);  // Starting position
        dungeon = new char[HEIGHT][WIDTH];
        initializeDungeon();
    }

    public void start() {
        System.out.println("Welcome to the Procedurally Generated Dungeon Crawler!");
        generateDungeon();  // Generate the dungeon layout
        placePlayer();
        while (true) {
            printDungeon();
            handleInput();
        }
    }

    // Initialize the dungeon with walls
    private void initializeDungeon() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                dungeon[i][j] = '#';  // '#' represents a wall
            }
        }
    }

    // Generate a dungeon using Depth-First Search (DFS)
    private void generateDungeon() {
        // Start the dungeon carving from the top-left corner (1,1)
        carvePassagesFrom(1, 1);
    }

    // Carve passages recursively using DFS
    private void carvePassagesFrom(int x, int y) {
        // Mark the current cell as a passage
        dungeon[y][x] = '.';

        // Randomly order the directions (up, down, left, right)
        int[] directions = new int[] {1, 2, 3, 4};
        shuffleArray(directions);

        // Try to carve a passage in each direction
        for (int direction : directions) {
            int dx = 0, dy = 0;

            switch (direction) {
                case 1: dy = -1; break; // Up
                case 2: dy = 1; break;  // Down
                case 3: dx = -1; break; // Left
                case 4: dx = 1; break;  // Right
            }

            // Check the position two steps away (because one step should remain a wall)
            int newX = x + 2 * dx;
            int newY = y + 2 * dy;

            // Ensure the new position is within bounds and uncarved
            if (newX > 0 && newX < WIDTH - 1 && newY > 0 && newY < HEIGHT - 1 && dungeon[newY][newX] == '#') {
                // Carve a passage through the wall between the current cell and the new cell
                dungeon[y + dy][x + dx] = '.';
                // Recur to carve the new cell
                carvePassagesFrom(newX, newY);
            }
        }
    }

    // Place the player at the starting position
    private void placePlayer() {
        player.setPosition(1, 1);
    }

    // Shuffle an array to randomize directions
    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private void printDungeon() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == player.getY() && j == player.getX()) {
                    System.out.print('P');  // Player position
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
            case 'w': player.moveUp(dungeon); break;
            case 'a': player.moveLeft(dungeon); break;
            case 's': player.moveDown(dungeon); break;
            case 'd': player.moveRight(dungeon); break;
            default: System.out.println("Invalid input!");
        }
    }
}