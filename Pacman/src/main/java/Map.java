public class Map {
    private Tile[][] tiles;

    public void SetupMap(){
        int xSize = Main.xSize, ySize = Main.ySize;

        tiles = new Tile[xSize][ySize];

        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                tiles[x][y] = new Tile(x,y);
            }
        }
    }

    //region Getters
    public Tile[][] getTiles() {
        return tiles;
    }

    //endregion
}
