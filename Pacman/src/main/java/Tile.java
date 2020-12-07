public class Tile {
    private int x, y;
    private Tile left, right, top, button;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //region Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //endregion
}
