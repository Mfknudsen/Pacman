public class Player implements Unit {

    // set spawn point (probably x: 13 or 14 and y:23)
    // move animation from one tile to the next
    // collision detection, here?

    private int x;
    private int y;

    public void Update() {

    }

    public void setSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {

    }

    public void collision() {

    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
