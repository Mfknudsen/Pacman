public class Tile {
    private int x, y;
    private Tile[] neighbors = new Tile[4];
    private TileType type;
    private Tile root;

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

    public Tile[] getTileNeighbors(){
        return neighbors;
    }

    public Tile getBlockedOrPortal(Tile currentTile, int direction){

        root = currentTile;

        if (currentTile.getTileNeighbors()[direction].getType() != TileType.BLOCKED)
            if (currentTile.getTileNeighbors()[direction].getType() != TileType.PORTAL)
                if (currentTile.getTileNeighbors()[direction].getType() != TileType.GhostRoom)
                    getBlockedOrPortal(currentTile.getTileNeighbors()[direction], direction);
        return root;
    }

//    public Tile getOpenOrPortal(Tile currentTile, int direction){
//
//    }

    public TileType getType() {
        return type;
    }
    //endregion

    //region Setters
    public void setType(TileType type) {
        this.type = type;
    }

    public void setNeighbors(Tile[] toAdd) {
        neighbors = toAdd;
    }
    //endregion
}
