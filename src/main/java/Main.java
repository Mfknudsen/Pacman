import processing.core.PApplet;

public class Main extends PApplet {
    public static Map map;
    public static int tileSize = 40;
    public static int xSize = 28, ySize = 31;
    public static int gameMapColorVCX = 87;
    private Ghost[] ghost;
    private Player player1;
    private boolean ghostReady = false;
    private int ghostTimer = 0;

    public void settings(){
        size(tileSize * (xSize + 1),tileSize * (ySize + 1));
    }

    public void setup(){
        map = new Map();
        map.SetupMap();

        ghost = new Ghost[4];
        Blinky blinky = new Blinky(11,13, 0);
        blinky.setColor(255, 0, 0);
        blinky.setScatter(map.getTileFromIndex(26, 1));
        ghost[0] = blinky;
        Inky inky = new Inky(11,15, 0.25f);
        inky.setColor(0, 255, 255);
        inky.setScatter(map.getTileFromIndex(26,29));
        inky.setBlinky(ghost[0]);
        ghost[1] = inky;
        Pinky pinky = new Pinky(16,13, 0.5f);
        pinky.setColor(255, 255, 0);
        pinky.setScatter(map.getTileFromIndex(1, 1));
        ghost[2] = pinky;
        Clyde clyde = new Clyde(16,15, 0.75f);
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

    public void draw() {
        Tile[][] draw = map.getTiles();
//        player1.Update();

        //Redraw Map
        for (Tile[] tileArr : draw) {
            for (Tile t : tileArr) {
                fill(255);

                if (t.getType() == TileType.BLOCKED)
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

        //Update and Draw Player
        player1.setCurrentTile(map.getTileFromIndex((int) player1.getX(), (int) player1.getY()));
        player1.Update();
        DrawPlayer(player1.getX(), player1.getY(), player1.getSize());

        //Update and Draw Ghosts
        if (ghostReady){
            ghostTimer++;

            if(ghostTimer % 180 == 0){
                for (Ghost g: ghost) {
                    if(g != null){

                    }
                }
            }
        }
        else{
            ghostReady = true;
            for (Ghost g: ghost){
                if(g != null){
                    if(g.getState() != GhostState.CHASE){
                        ghostReady = false;
                        break;
                    }
                }
            }
        }

        for (Ghost g : ghost) {
            if (g != null) {
                g.setCurrent(map.getTileFromIndex((int) g.getX(), (int) g.getY()));
                g.Update();
                DrawGhost(g.getX(), g.getY(), g.getSize(), g.getColor(), g.getDirection(), g.state);
            }
        }

        //Debug Coordinates
/*
            for (Tile[] set : draw) {
                for (Tile tile : set) {
                    fill(0);
                    text("X: " + (tile.getX()),
                            (tile.getX() * tileSize) + tileSize - (tileSize / 2 - (tileSize * 0.1f)),
                            (tile.getY() * tileSize) + tileSize - (tileSize / 2) + (tileSize * 0.5f));
                    text("Y: " + (tile.getY()),
                            (tile.getX() * tileSize) + tileSize - (tileSize / 2 - (tileSize * 0.1f)),
                            (tile.getY() * tileSize) + tileSize - (tileSize / 2) + (tileSize * 0.75f));
                }
            }
*/
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

    void DrawGhost(float x, float y, int size, float[] color, Direction direction, GhostState state) {
        if(state != GhostState.RETURN) {
            fill(color[0], color[1], color[2]);
            rect((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size) / 2,
                    (y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size) / 2,
                    size,
                    size);
        }
        float centerX = ((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2) + size/2;
        float centerY = ((y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2) + size/2;
        fill(255);
        rect(centerX - 10, centerY - 10, 8, 14);
        rect(centerX + 2, centerY - 10, 8, 14);

        float   xLeft = centerX - 10 + 4,
                xRight = centerX + 2 + 4,
                yLeft = centerY - 10  + 7,
                yRight = centerY - 10 + 7;

        if(direction == Direction.DOWN){
            yLeft += 5;
            yRight += 5;
        }
        else if(direction == Direction.UP){
            yLeft -= 4;
            yRight -= 4;
        }
        else if(direction == Direction.LEFT){
            xLeft -= 2;
            xRight -= 2;
        }
        else{
            xLeft += 2;
            xRight += 2;
        }

        fill(0);
        rect(xLeft - 2, yLeft - 2, 4, 4);
        rect(xRight - 2, yRight - 2, 4, 4);
    }

    void DrawPlayer(float x, float y, int size) {
        fill(130,30,200);
        rect((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                (y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                size,
                size);
    }
}
