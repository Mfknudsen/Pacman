import processing.core.PApplet;

public class Main extends PApplet {
    private Map map;
    public static int size = 40, tileSize = 40;
    public static int xSize = 28, ySize = 31;
    public static int gameMapColorVCX = 87;
    Pathfinder pathfinder;

    public void settings(){
        size(tileSize * (xSize + 1),tileSize * (ySize + 1));
    }

    public void setup(){
        map = new Map();
        map.SetupMap();

        pathfinder = new Pathfinder();

        pathfinder.setStartTile(map.getTileFromCoordinates(80, 60));
        pathfinder.setEndTile(map.getTileFromCoordinates(980, 940));

        pathfinder.FindPath();
    }

    public void draw(){
        Tile[][] draw = map.getTiles();

        pathfinder.setEndTile(map.getTileFromCoordinates(
                mouseX,
                mouseY
        ));
        pathfinder.FindPath();

        for (Tile[] tileArr: draw) {
            for (Tile t : tileArr) {
                fill(255);

                if(t.getType() == TileType.BLOCKED)
                    fill(gameMapColorVCX);

                // yellow color for ghost room
                if (t.getType() == TileType.GhostRoom)
                    fill(255, 255, 0);

                // light green color for portal
                if (t.getType() == TileType.PORTAL)
                    fill(145, 255, 187);

                DrawTile(t);
            }
        }

        //Debug Pathfinding
        for(PathNode node: pathfinder.closedNodes){
            Tile t = node.getTile();

            fill(255,0,0);
            DrawTile(t);
        }
        for(PathNode node: pathfinder.openNodes){
            Tile t = node.getTile();

            fill(0,0,255);
            DrawTile(t);
        }
        for(PathNode node: pathfinder.result){
            Tile t = node.getTile();

            fill(0,255,255);
            DrawTile(t);
        }

        Tile t = map.getTileFromCoordinates(80, 60);
        fill(0,255,0);
        DrawTile(t);

        t = map.getTileFromCoordinates(mouseX, mouseY);
        DrawTile(t);

        for (Tile[] set: draw){
            for (Tile tile: set){
                fill(0);
                text("X: " + (tile.getX()),
                        (tile.getX()  * tileSize) + tileSize - (tileSize/2 - (tileSize * 0.1f)),
                        (tile.getY()  * tileSize) + tileSize - (tileSize/2) + (tileSize * 0.5f));
                text("Y: " + (tile.getY()),
                        (tile.getX()  * tileSize) + tileSize - (tileSize/2 - (tileSize * 0.1f)),
                        (tile.getY()  * tileSize) + tileSize - (tileSize/2) + (tileSize * 0.75f));
            }
        }
    }

    public static void main(String[] args){
        PApplet.main("Main");
    }

    void DrawTile(Tile tile) {
        int tileSize = Main.tileSize;

        if (tile != null) {
            rect((tile.getX() * tileSize) + tileSize - (tileSize / 2),
                    (tile.getY() * tileSize) + tileSize - (tileSize / 2),
                    tileSize,
                    tileSize);
        }
    }
}
