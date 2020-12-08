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

        pathfinder.setStartTile(map.getTileFromCoordinates(50, 60));
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

                rect((t.getX() * tileSize) + tileSize - (tileSize / 2),
                        (t.getY() * tileSize) + tileSize - (tileSize / 2),
                        tileSize,
                        tileSize);
            }
        }

        /*
        for (PathNode node: pathfinder.closedNodes){
            rect((node.getTile().getX()  * tileSize) + tileSize - (tileSize/2),
                    (node.getTile().getY()  * tileSize) + tileSize - (tileSize/2),
                    tileSize,
                    tileSize);
        }
         */

        //Debug Pathfinding
        for(PathNode node: pathfinder.closedNodes){
            Tile t = node.getTile();

            fill(255,0,0);
            rect((t.getX()  * tileSize) + tileSize - (tileSize/2),
                    (t.getY()  * tileSize) + tileSize - (tileSize/2),
                    tileSize,
                    tileSize);
        }
        for(PathNode node: pathfinder.openNodes){
            Tile t = node.getTile();

            fill(0,0,255);
            rect((t.getX()  * tileSize) + tileSize - (tileSize/2),
                    (t.getY()  * tileSize) + tileSize - (tileSize/2),
                    tileSize,
                    tileSize);
        }
        for(PathNode node: pathfinder.result){
            Tile t = node.getTile();

            fill(0,255,255);
            rect((t.getX()  * tileSize) + tileSize - (tileSize/2),
                    (t.getY()  * tileSize) + tileSize - (tileSize/2),
                    tileSize,
                    tileSize);
        }

        Tile t = map.getTileFromCoordinates(50, 60);
        fill(0,255,0);
        rect((t.getX()  * tileSize) + tileSize - (tileSize/2),
                (t.getY()  * tileSize) + tileSize - (tileSize/2),
                tileSize,
                tileSize);
        t = map.getTileFromCoordinates(980, 940);
        rect((t.getX()  * tileSize) + tileSize - (tileSize/2),
                (t.getY()  * tileSize) + tileSize - (tileSize/2),
                tileSize,
                tileSize);

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
}
