package dungeoncrawler;

public class Player {
    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp(char[][] dungeon) {
        if (y > 0 && dungeon[y - 1][x] == '.') {
            y--;
        }
    }

    public void moveDown(char[][] dungeon) {
        if (y < dungeon.length - 1 && dungeon[y + 1][x] == '.') {
            y++;
        }
    }

    public void moveLeft(char[][] dungeon) {
        if (x > 0 && dungeon[y][x - 1] == '.') {
            x--;
        }
    }

    public void moveRight(char[][] dungeon) {
        if (x < dungeon[0].length - 1 && dungeon[y][x + 1] == '.') {
            x++;
        }
    }
}