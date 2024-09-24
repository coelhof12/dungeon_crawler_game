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

    public void moveUp() {
        if (y > 0) {
            y--;
        }
    }

    public void moveDown() {
        if (y < 9) {  // Prevent moving out of bounds
            y++;
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x--;
        }
    }

    public void moveRight() {
        if (x < 9) {  // Prevent moving out of bounds
            x++;
        }
    }
}
