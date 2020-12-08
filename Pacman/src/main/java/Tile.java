public class Tile {
    private int x, y;
    private Tile[] neighbors = new Tile[4];
    private TileType type;

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

    public TileType getType() {
        return type;
    }
    //endregion

    //region Setters
    public void setType(TileType type) {
        this.type = type;
    }

    public void setNeighbors(Tile toAdd) {
        for(int i = 0; i < 4; i++){
            if(neighbors[i] == null) {
                neighbors[i] = toAdd;
                break;
            }
        }
    }
    //endregion
}
