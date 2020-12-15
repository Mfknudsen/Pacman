import processing.core.PApplet;

public class Main extends PApplet {
    public static Map map;
    public static int tileSize = 40;
    public static int xSize = 28, ySize = 31;
    public static int gameMapColorVCX = 87;
    private Ghost[] ghost;
    private Player player;
    private boolean ghostReady = false;
    private int ghostTimer = 0;

    private int timer;

    public void settings(){
        size(tileSize * (xSize + 1),tileSize * (ySize + 1));
    }

    public void setup(){
        map = new Map();
        //Setup Fruits and PowerUps
        map.createFruit(1,1);
        map.createPowerUp(2,29);
        map.SetupMap();

        //Setup Ghosts
        ghost = new Ghost[4];
        Blinky blinky = new Blinky(11,13, 0.25f);
        blinky.setColor(255, 0, 0);
        blinky.setScatter(map.getTileFromIndex(26, 1));
        ghost[0] = blinky;
        Inky inky = new Inky(11,15, 0.5f);
        inky.setColor(0, 255, 255);
        inky.setScatter(map.getTileFromIndex(26,29));
        inky.setBlinky(ghost[0]);
        ghost[1] = inky;
        Pinky pinky = new Pinky(16,13, 0.75f);
        pinky.setColor(255, 255, 0);
        pinky.setScatter(map.getTileFromIndex(1, 1));
        ghost[2] = pinky;
        Clyde clyde = new Clyde(16,15, 1.0f);
        clyde.setColor(255, 100, 100);
        clyde.setScatter(map.getTileFromIndex(1, 29));
        ghost[3] = clyde;

        //Setup Player
        player = new Player();
        player.setSpawnPoint(14, 23);

        //Set Ghost target to Player
        for (Ghost g: ghost) {
            if(g != null)
                g.setTarget(player);
        }
    }

    public void draw() {
        Tile[][] draw = map.getTiles();
        player.Update();

        //Redraw Map
        for (Tile[] tileArr : draw) {
            for (Tile t : tileArr) {
                fill(255);
                if (t.getType() == TileType.BLOCKED)
                    fill(gameMapColorVCX);
                // yellow color for ghost room
                if (t.getType() == TileType.GhostRoom)
                    fill(100, 100, 0);
                // light green color for portal
                if (t.getType() == TileType.PORTAL)
                    fill(145, 255, 187);

                DrawTile(t);
            }
        }

        //Draw Pebbles, PowerUps and Fruits
        for (Pebble p : map.getPebbles()) {
            DrawPebble(p.getX(), p.getY());
        }
        for (PowerUp p : map.getPowerUps()) {
            DrawPowerUp(p.getX(), p.getY());
        }
        for(Fruit f : map.getFruits()){
            DrawFruits(f.getX(), f.getY());
        }


        //Update and Draw Player
        player.setCurrentTile(map.getTileFromIndex((int) player.getX(), (int) player.getY()));
        player.Update();
        DrawPlayer(player.getX(), player.getY(), player.getSize());

        //Update and Draw Ghosts
        if (ghostReady) {
            if (ghost[0].getState() != GhostState.FLEE)
                ghostTimer++;

            if (ghostTimer % 480 == 1) {
                for (Ghost g : ghost) {
                    if (g != null) {
                        if (g.getState() == GhostState.CHASE)
                            g.setState(GhostState.SCATTER);
                        else
                            g.setState(GhostState.CHASE);
                    }
                }
            }
        } else {
            ghostReady = true;
            for (Ghost g : ghost) {
                if (g != null) {
                    if (g.getState() != GhostState.CHASE) {
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
        player.onKeyPressed(key);
    }

    public static void main(String[] args){
        PApplet.main("Main");
    }

    private void DrawTile(Tile tile) {
        if (tile != null) {
            rect((tile.getX() * tileSize) + tileSize - (tileSize / 2),
                    (tile.getY() * tileSize) + tileSize - (tileSize / 2),
                    tileSize,
                    tileSize);
        }
    }

    private void DrawGhost(float x, float y, int size, float[] color, Direction direction, GhostState state) {
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

    private void DrawPlayer(float x, float y, int size) {
        fill(130,30,200);
        rect((x * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                (y * tileSize) + tileSize - (tileSize / 2) + (tileSize - size)/ 2,
                size,
                size);
    }

    private void DrawPebble(float x, float y){
        fill(255, 255, 0);
        ellipse((x * tileSize + tileSize),
                (y * tileSize + tileSize),
                10.0f, 10.0f);
    }

    private void DrawPowerUp(float x, float y){
        fill(175, 0, 0);
        ellipse((x * tileSize + tileSize),
                (y * tileSize + tileSize),
                15, 15);
    }

    private void DrawFruits(float x, float y){
        fill(50, 255, 50);
        ellipse((x * tileSize + tileSize),
                (y * tileSize + tileSize),
                15, 15);
    }
}
