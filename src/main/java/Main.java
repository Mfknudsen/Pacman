import processing.core.PApplet;

public class Main extends PApplet {
    public static Map map;
    public static int tileSize = 40;
    public static int xSize = 28, ySize = 31;
    public static int gameMapColorVCX = 87;
    private Ghost[] ghost;
    private Player player1;

    public void settings(){
        size(tileSize * (xSize + 1),tileSize * (ySize + 1));
    }

    public void setup(){
        map = new Map();
        map.SetupMap();

        ghost = new Ghost[4];
        Blinky blinky = new Blinky(11,13);
        blinky.setColor(255, 0, 0);
        blinky.setScatter(map.getTileFromIndex(26, 1));
        ghost[0] = blinky;
        Inky inky = new Inky(11,15);
        inky.setColor(0, 255, 255);
        inky.setScatter(map.getTileFromIndex(26,29));
        inky.setBlinky(ghost[0]);
        ghost[1] = inky;
        Pinky pinky = new Pinky(16,13);
        pinky.setColor(255, 255, 0);
        pinky.setScatter(map.getTileFromIndex(1, 1));
        ghost[2] = pinky;
        Clyde clyde = new Clyde(16,15);
        clyde.setColor(255, 100, 100);
        clyde.setScatter(map.getTileFromIndex(1, 29));
        ghost[3] = clyde;

        player1 = new Player();
        player1.setSpawnPoint(14, 23);

        for (Ghost g: ghost) {
            if(g != null)
                g.setTarget(player1);
        }
    }

    public void draw(){
        Tile[][] draw = map.getTiles();
        player1.Update();

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

        player1.setCurrentTile(map.getTileFromIndex((int) player1.getX(), (int) player1.getY()));
        player1.Update();
        DrawPlayer(player1.getX(), player1.getY(), player1.getSize());


        fill(0,255,0);
        Tile t = map.getTileFromCoordinates(mouseX, mouseY);
        DrawTile(t);

        for (Ghost g: ghost) {
            if (g != null) {
                g.setCurrent(map.getTileFromIndex((int)g.getX(), (int)g.getY()));
                g.Update();
                DrawGhost(g.getX(), g.getY(), g.getSize(), g.getColor());
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

    public void keyPressed(){
        player1.onKeyPressed(key);
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

    void DrawGhost(float x, float y, int size, float[] color) {
        fill(color[0], color[1], color[2]);
        rect((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                (y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                size,
                size);
    }

    void DrawPlayer(float x, float y, int size) {
        fill(130,30,200);
        rect((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                (y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                size,
                size);
    }
}
