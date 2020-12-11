import processing.core.PApplet;

public class Main extends PApplet {
    public static Map map;
    public static int size = 40, tileSize = 40;
    public static int xSize = 28, ySize = 31;
    public static int gameMapColorVCX = 87;
    Ghost[] ghost;

    public void settings(){
        size(tileSize * (xSize + 1),tileSize * (ySize + 1));
    }

    public void setup(){
        map = new Map();
        map.SetupMap();


        ghost = new Ghost[4];
        ghost[0] = new Ghost(11,13);
        ghost[1] = new Ghost(11,15);
        ghost[2] = new Ghost(16,13);
        ghost[3] = new Ghost(16,15);
    }

    public void draw(){
        Tile[][] draw = map.getTiles();

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

        fill(0,255,0);
        Tile t = map.getTileFromCoordinates(mouseX, mouseY);
        DrawTile(t);

        for (Ghost g: ghost) {
            if (g != null) {
                g.setCurrent(map.getTileFromIndex((int)g.getX(), (int)g.getY()));
                g.setTarget(map.getTileFromCoordinates(mouseX, mouseY));
                g.Update();
                DrawGhost(g.getX(), g.getY(), g.getSize());
            }
        }

        //Debug Coordinates
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
        if (tile != null) {
            rect((tile.getX() * tileSize) + tileSize - (tileSize / 2),
                    (tile.getY() * tileSize) + tileSize - (tileSize / 2),
                    tileSize,
                    tileSize);
        }
    }

    void DrawGhost(float x, float y, int size) {
        fill(0,0,255);
        rect((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                (y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                size,
                size);
    }
}
