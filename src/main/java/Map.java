import java.util.ArrayList;
import java.util.List;

public class Map {
    private Tile[][] tiles;
    private List<PowerUp> powerUps = new ArrayList<PowerUp>();
    private List<Pebble> pebbles = new ArrayList<Pebble>();
    private List<Fruit> fruits = new ArrayList<Fruit>();

    public void SetupMap() {
        int xSize = Main.xSize, ySize = Main.ySize;

        tiles = new Tile[xSize][ySize];

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                tiles[x][y] = new Tile(x, y);

                //region outer walls
                if (
                        (tiles[x][y].getX() <= 28 && (tiles[x][y].getY() == 0 || tiles[x][y].getY() == 30)) // top and bottom
                                || ((tiles[x][y].getX() == 0 || tiles[x][y].getX() == xSize - 1) && (tiles[x][y].getY() <= 9 || (tiles[x][y].getY() <= 30 && tiles[x][y].getY() >= 20))) // sides
                                || ((tiles[x][y].getX() <= 5 || (tiles[x][y].getX() >= xSize - 6 && tiles[x][y].getX() <= xSize - 1)) && (tiles[x][y].getY() == 9 || tiles[x][y].getY() == 13 || tiles[x][y].getY() == 15 || tiles[x][y].getY() == 19)) // tubes
                                || ((tiles[x][y].getX() == 5 || tiles[x][y].getX() == 22) && ((tiles[x][y].getY() <= 12 && tiles[x][y].getY() >= 10) || (tiles[x][y].getY() <= 18 && tiles[x][y].getY() >= 16))) // tube sides
                )
                    tiles[x][y].setType(TileType.BLOCKED);
                    //endregion

                    //region top middle block
                else if (
                        ((tiles[x][y].getX() >= 13 && tiles[x][y].getX() <= 14) && (tiles[x][y].getY() <= 4 && tiles[x][y].getY() >= 1))
                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region top rectangles
                else if (
                        ((tiles[x][y].getX() >= 2) && (tiles[x][y].getX() <= 5) || (tiles[x][y].getX() >= 7) && (tiles[x][y].getX() <= 11) || (tiles[x][y].getX() >= 16) && (tiles[x][y].getX() <= 20) || (tiles[x][y].getX() >= 22) && (tiles[x][y].getX() <= 25))
                                && (tiles[x][y].getY() >= 2 && tiles[x][y].getY() <= 4))
                    tiles[x][y].setType(TileType.BLOCKED);
                    //endregion

                    //region second set rectangles below top rectangles
                else if (
                        ((tiles[x][y].getX() >= 2) && (tiles[x][y].getX() <= 5) || (tiles[x][y].getX() >= 22) && (tiles[x][y].getX() <= 25))
                                && (tiles[x][y].getY() >= 6 && tiles[x][y].getY() <= 7))
                    tiles[x][y].setType(TileType.BLOCKED);
                    //endregion

                    //region top t-blocks
                else if (
                        (((tiles[x][y].getX() >= 7 && tiles[x][y].getX() <= 8) && (tiles[x][y].getY() >= 6 && tiles[x][y].getY() <= 13)) || ((tiles[x][y].getX() >= 7 && tiles[x][y].getX() <= 11) && (tiles[x][y].getY() >= 9 && tiles[x][y].getY() <= 10))) //T-block left
                                || (((tiles[x][y].getX() >= 10 && tiles[x][y].getX() <= 17) && (tiles[x][y].getY() >= 6 && tiles[x][y].getY() <= 7)) || ((tiles[x][y].getX() >= 13 && tiles[x][y].getX() <= 14) && (tiles[x][y].getY() >= 8 && tiles[x][y].getY() <= 10))) //T-block middle
                                || (((tiles[x][y].getX() >= 19 && tiles[x][y].getX() <= 20) && (tiles[x][y].getY() >= 6 && tiles[x][y].getY() <= 13)) || ((tiles[x][y].getX() >= 16 && tiles[x][y].getX() <= 20) && (tiles[x][y].getY() >= 9 && tiles[x][y].getY() <= 10))) // T-block right
                )
                    tiles[x][y].setType(TileType.BLOCKED);
                    //endregion

                    //region vertical rectangles
                else if ((tiles[x][y].getX() >= 7 && tiles[x][y].getX() <= 8) && (tiles[x][y].getY() >= 15 && tiles[x][y].getY() <= 19)
                        || (tiles[x][y].getX() >= 19 && tiles[x][y].getX() <= 20) && (tiles[x][y].getY() >= 15 && tiles[x][y].getY() <= 19))
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region ghost box
                else if (
                        ((tiles[x][y].getX() >= 10 && tiles[x][y].getX() <= 12) || (tiles[x][y].getX() >= 15 && tiles[x][y].getX() <= 17)) && (tiles[x][y].getY() == 12)
                                || (tiles[x][y].getX() == 10 || tiles[x][y].getX() == 17) && (tiles[x][y].getY() >= 12 && tiles[x][y].getY() <= 16)
                                || ((tiles[x][y].getX() >= 10 && tiles[x][y].getX() <= 17) && (tiles[x][y].getY() == 16))
                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region middle T-block (under ghost box)
                else if (
                        ((tiles[x][y].getX() >= 10 && tiles[x][y].getX() <= 17) && (tiles[x][y].getY() >= 18 && tiles[x][y].getY() <= 19)
                                || (tiles[x][y].getX() >= 13 && tiles[x][y].getX() <= 14) && (tiles[x][y].getY() >= 20 && tiles[x][y].getY() <= 22))
                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region L shapes
                else if (
                        (((tiles[x][y].getX() >= 2 && tiles[x][y].getX() <= 5) || (tiles[x][y].getX() >= 22 && tiles[x][y].getX() <= 25)) && (tiles[x][y].getY() >= 21 && tiles[x][y].getY() <= 22)
                                || ((tiles[x][y].getX() >= 4 && tiles[x][y].getX() <= 5) || (tiles[x][y].getX() >= 22 && tiles[x][y].getX() <= 23)) && (tiles[x][y].getY() >= 23 && tiles[x][y].getY() <= 25))
                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region Bottom rectangles
                else if (
                        (((tiles[x][y].getX() >= 7 && tiles[x][y].getX() <= 11) || (tiles[x][y].getX() >= 16 && tiles[x][y].getX() <= 20)) && (tiles[x][y].getY() >= 21 && tiles[x][y].getY() <= 22))
                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region Bottom T-shapes
                else if (
                        ((tiles[x][y].getX() >= 2 && tiles[x][y].getX() <= 11) && (tiles[x][y].getY() >= 27 && tiles[x][y].getY() <= 28))
                                || ((tiles[x][y].getX() >= 7 && tiles[x][y].getX() <= 8) && (tiles[x][y].getY() >= 24 && tiles[x][y].getY() <= 28)) // Left T-shape

                                || ((tiles[x][y].getX() >= 10 && tiles[x][y].getX() <= 17) && (tiles[x][y].getY() >= 24 && tiles[x][y].getY() <= 25))
                                || ((tiles[x][y].getX() >= 13 && tiles[x][y].getX() <= 14) && (tiles[x][y].getY() >= 26 && tiles[x][y].getY() <= 28))// Middle T-shape

                                || (((tiles[x][y].getX() >= 16 && tiles[x][y].getX() <= 25)) && (tiles[x][y].getY() >= 27 && tiles[x][y].getY() <= 28))
                                || ((tiles[x][y].getX() >= 19 && tiles[x][y].getX() <= 20) && (tiles[x][y].getY() >= 24 && tiles[x][y].getY() <= 28))// Right T-shape
                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region Bottom wall bumps
                else if (
                        ((tiles[x][y].getX() >= 1 && tiles[x][y].getX() <= 2) && (tiles[x][y].getY() >= 24 && tiles[x][y].getY() <= 25)) // Left bump
                                || ((tiles[x][y].getX() >= xSize - 3 && tiles[x][y].getX() <= xSize - 2) && (tiles[x][y].getY() >= 24 && tiles[x][y].getY() <= 25)) // Right bump

                )
                    tiles[x][y].setType(TileType.BLOCKED);

                    //endregion

                    //region Ghostroom
                else if (
                        ((tiles[x][y].getX() >= 13 && tiles[x][y].getX() <= 14) && (tiles[x][y].getY() == 12)) // door
                                || ((tiles[x][y].getX() >= 11 && tiles[x][y].getX() <= 16) && (tiles[x][y].getY() >= 13 && tiles[x][y].getY() <= 15)) // room

                )
                    tiles[x][y].setType(TileType.GhostRoom);

                    //endregion

                    //region Portals
                else if (
                        ((tiles[x][y].getX() == 0 || tiles[x][y].getX() == 27) && (tiles[x][y].getY() == 14))
                )
                    tiles[x][y].setType(TileType.PORTAL);

                    //endregion

                else {
                    tiles[x][y].setType(TileType.WALKABLE);
                }
            }
        }

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                Tile tile = tiles[x][y];
                Tile[] neighbors = new Tile[4];

                if (x - 1 >= 0)
                    neighbors[0] = tiles[x - 1][y];
                if (x + 1 < xSize)
                    neighbors[1] = tiles[x + 1][y];
                if (y - 1 >= 0)
                    neighbors[2] = tiles[x][y - 1];
                if (y + 1 < ySize)
                    neighbors[3] = tiles[x][y + 1];

                tile.setNeighbors(neighbors);
            }
        }

        Pathfinder pebbleSetter = new Pathfinder();
        pebbleSetter.setStartTile(getTileFromIndex(1, 0));
        pebbleSetter.setEndTile(getTileFromIndex(27, 30));
        pebbleSetter.FindPath();
        for (PathNode node : pebbleSetter.getClosedNodes()) {
            if (node.getTile().getType() == TileType.WALKABLE) {
                boolean isEmpty = true;

                for (PowerUp power : powerUps) {
                    if (power.getX() == node.getTile().getX() && power.getY() == node.getTile().getY()) {
                        isEmpty = false;
                        break;
                    }
                }
                for (Fruit fruit : fruits) {
                    if (fruit.getX() == node.getTile().getX() && fruit.getY() == node.getTile().getY()) {
                        isEmpty = false;
                        break;
                    }
                }

                if (isEmpty) {
                    Pebble pebble = new Pebble(node.getTile().getX(), node.getTile().getY());
                    pebbles.add(pebble);
                }
            }
        }
    }

    public void createFruit(int x, int y){
        Fruit fruit = new Fruit(x, y);
        fruits.add(fruit);
    }

    public void createPowerUp(int x, int y){
        PowerUp powerUp = new PowerUp(x, y);
        powerUps.add(powerUp);
    }

    //region Getters
    public Tile[][] getTiles() {
        return tiles;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Pebble> getPebbles() {
        return pebbles;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public Tile getTileFromCoordinates(float x, float y){

        for (Tile[] set: tiles) {
            for (Tile tile: set){
                if(x >= (tile.getX() * Main.tileSize + Main.tileSize * 0.5f) && x < (tile.getX() * Main.tileSize + Main.tileSize * 1.5f) &&
                y >= (tile.getY() * Main.tileSize + Main.tileSize * 0.5f) && y < (tile.getY() * Main.tileSize + Main.tileSize * 1.5f))
                    return tile;
            }
        }

        return null;
    }

    public Tile getTileFromIndex(int x, int y){
        return tiles[x][y];
    }
    //endregion
}
