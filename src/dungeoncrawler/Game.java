package dungeoncrawler;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements KeyboardHandler {

    private Player player;
    private char[][] dungeon;
    private final int WIDTH = 20;  // Width of the dungeon
    private final int HEIGHT = 10; // Height of the dungeon
    private final int CELL_SIZE = 40; // Size of each cell in pixels
    private Random rand = new Random();

    private Rectangle[][] grid;
    private Rectangle playerGraphic;  // Rectangle to represent the player graphically

    // List to store enemies
    private List<Enemy> enemies = new ArrayList<>();

    public Game() {
        player = new Player(1, 1);  // Starting position
        dungeon = new char[HEIGHT][WIDTH];
        initializeDungeon();
        initializeGraphics(); // Initialize graphical elements
        setupKeyboard();      // Set up keyboard events
    }

    public void start() {
        System.out.println("Welcome to the Procedurally Generated Dungeon Crawler!");
        generateDungeon();  // Generate the dungeon layout
        placePlayer();
        placeExit();
        spawnEnemies(3);  // Spawns 3 enemies

        // Game loop (you can add more logic here if needed)
        while (true) {
            // In a real-time game, you would use a timer or game loop
        }
    }

    // Initialize the graphical grid for the dungeon
    private void initializeGraphics() {
        grid = new Rectangle[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = new Rectangle(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                grid[i][j].setColor(Color.BLACK);  // Initially draw as black
                grid[i][j].draw();
            }
        }

        // Create graphical representation of the player
        playerGraphic = new Rectangle(player.getX() * CELL_SIZE, player.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        playerGraphic.setColor(Color.RED);
        playerGraphic.fill();
    }

    // Handle player movement and enemy moves via keyboard input
    private void setupKeyboard() {
        Keyboard keyboard = new Keyboard(this);

        // Set up keyboard events for movement
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        // Register keyboard events
        keyboard.addEventListener(up);
        keyboard.addEventListener(left);
        keyboard.addEventListener(down);
        keyboard.addEventListener(right);
    }

    // Override the keyPressed method to handle player movement
    @Override
    public void keyPressed(KeyboardEvent event) {
        switch (event.getKey()) {
            case KeyboardEvent.KEY_W:
                movePlayer(0, -1);  // Move up
                break;
            case KeyboardEvent.KEY_A:
                movePlayer(-1, 0);  // Move left
                break;
            case KeyboardEvent.KEY_S:
                movePlayer(0, 1);   // Move down
                break;
            case KeyboardEvent.KEY_D:
                movePlayer(1, 0);   // Move right
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Not handling key release events for now
    }

    // Move the player and update the graphical position
    private void movePlayer(int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        // Check if the move is within bounds and the player isn't walking through walls
        if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT) {
            player.setPosition(newX, newY);
            playerGraphic.translate(dx * CELL_SIZE, dy * CELL_SIZE);  // Move the player graphic
        }
    }

    // Other existing game logic: dungeon generation, spawning enemies, etc..

    // For instance, spawning enemies could involve creating graphical ellipses for them.
}