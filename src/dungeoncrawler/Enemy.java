package dungeoncrawler;

import java.util.Random;

public class Enemy {
    private int x, y;
    private Random rand = new Random();

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(char[][] dungeon) {
        // Randomly move the enemy up, down, left, or right if it's not blocked
        int direction = rand.nextInt(4);  // 0 = up, 1 = down, 2 = left, 3 = right
        switch (direction) {
            case 0: if (y > 0 && dungeon[y - 1][x] == '.') y--; break;  // Move up
            case 1: if (y < dungeon.length - 1 && dungeon[y + 1][x] == '.') y++; break;  // Move down
            case 2: if (x > 0 && dungeon[y][x - 1] == '.') x--; break;  // Move left
            case 3: if (x < dungeon[0].length - 1 && dungeon[y][x + 1] == '.') x++; break;  // Move right
        }
    }
}