public class PathNode {
    PathNode parent;
    Tile tile;
    float n, h;

    public PathNode(PathNode parent, Tile tile, float n, float h) {
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
        return n + h;
    }
    //endregion
}
