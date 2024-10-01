package dungeoncrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private char[][] dungeon;
    private final int WIDTH = 20;  // Width of the dungeon
    private final int HEIGHT = 10; // Height of the dungeon
    private Random rand = new Random();
    private Scanner scanner;  // Reusable Scanner for player input

    // List to store enemies
    private List<Enemy> enemies = new ArrayList<>();

    public Game() {
        player = new Player(1, 1);  // Starting position
        dungeon = new char[HEIGHT][WIDTH];
        initializeDungeon();
        scanner = new Scanner(System.in);  // Initialize Scanner once
    }

    public void start() {
        System.out.println("Welcome to the Procedurally Generated Dungeon Crawler!");
        generateDungeon();  // Generate the dungeon layout
        placePlayer();
        placeExit();
        spawnEnemies(3);  // Spawns 3 enemies
        while (true) {
            printDungeon();
            handleInput();
            moveEnemies();  // Move enemies after each player turn
            handleCombat(); // Check for player and enemy combat interactions
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
        carvePassagesFrom(1, 1);  // Start dungeon carving from the top-left corner
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

            // Check the position two steps away
            int newX = x + 2 * dx;
            int newY = y + 2 * dy;

            // Ensure the new position is within bounds and uncarved
            if (newX > 0 && newX < WIDTH - 1 && newY > 0 && newY < HEIGHT - 1 && dungeon[newY][newX] == '#') {
                dungeon[y + dy][x + dx] = '.';  // Carve a passage between the cells
                carvePassagesFrom(newX, newY);
            }
        }
    }

    // Place the player at the starting position
    private void placePlayer() {
        player.setPosition(1, 1);
    }

    // Place the exit in a random location far from the player
    private void placeExit() {
        int exitX, exitY;
        do {
            exitX = rand.nextInt(WIDTH - 2) + 1;
            exitY = rand.nextInt(HEIGHT - 2) + 1;
        } while (Math.abs(exitX - player.getX()) + Math.abs(exitY - player.getY()) < 10);  // Ensure it's far from player

        dungeon[exitY][exitX] = 'X';  // 'X' represents the exit
    }

    // Spawn enemies in random locations
    private void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            int ex, ey;
            do {
                ex = rand.nextInt(WIDTH - 2) + 1;
                ey = rand.nextInt(HEIGHT - 2) + 1;
            } while (dungeon[ey][ex] != '.');  // Ensure enemy spawns in an open space
            enemies.add(new Enemy(ex, ey));
        }
    }

    // Move enemies randomly each turn
    private void moveEnemies() {
        for (Enemy enemy : enemies) {
            enemy.move(dungeon);
        }
    }

    // Handle player combat with enemies
    private void handleCombat() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.getX() == player.getX() && enemy.getY() == player.getY()) {
                System.out.println("You attacked an enemy!");
                enemies.remove(i);  // Remove defeated enemy
                break;
            }
        }
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

    // Check if an enemy is at a specific location
    private boolean isEnemyAt(int x, int y) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y) {
                return true;
            }
        }
        return false;
    }

    // Print the dungeon, including the player and enemies
    private void printDungeon() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == player.getY() && j == player.getX()) {
                    System.out.print('P');  // Player position
                } else if (isEnemyAt(j, i)) {
                    System.out.print('E');  // Enemy position
                } else {
                    System.out.print(dungeon[i][j]);
                }
            }
            System.out.println();
        }
    }

    // Handle player input and movement
    private void handleInput() {
        System.out.println("Move (w=up, a=left, s=down, d=right): ");
        char input = scanner.next().charAt(0);  // Reuse Scanner for input

        switch (input) {
            case 'w': player.moveUp(dungeon); break;
            case 'a': player.moveLeft(dungeon); break;
            case 's': player.moveDown(dungeon); break;
            case 'd': player.moveRight(dungeon); break;
            default: System.out.println("Invalid input!");
        }

        // Check if the player reached the exit
        if (dungeon[player.getY()][player.getX()] == 'X') {
            System.out.println("Congratulations! You found the exit!");
            System.exit(0);  // Exit the game
        }
    }
}