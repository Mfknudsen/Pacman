public class PathNode {
    PathNode parent;
    Tile tile;
    float n, h;

    public PathNode(PathNode parent, Tile tile) {
        this.parent = parent;
        this.tile = tile;
        this.n = n;
        this.h = h;
    }

    //region Getters
    public Tile getTile() {
        return tile;
    }

    public float getValue(){
        return h;
    }
    //endregion

    //region Setters
    public void setValue(int x, int y){
        h = (float) Math.sqrt(Math.pow(x - tile.getX(), 2) + Math.pow(y - tile.getY(), 2));
    }
    //endregion
}
