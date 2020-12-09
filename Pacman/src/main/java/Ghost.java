public class Ghost implements Unit, Scoring{

    // set spawn point (probably x: 11 to 15  and y: 13 to 15)
    // move animation from one tile to the next
    // collision detection, here?

    private int x;
    private int y;

    public void setSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {

    }

    public void collision() {

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int addScore() {
        return 0;
    }
}
