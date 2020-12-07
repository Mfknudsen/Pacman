import processing.core.PApplet;

public class Main extends PApplet {
    private Map map;
    public static int size = 40, tileSize = 45;
    public static int xSize = 28, ySize = 31;

    public void settings(){
        size(tileSize * (xSize + 1),tileSize * (ySize + 1));
    }

    public void setup(){
        map = new Map();
        map.SetupMap();
    }

    public void draw(){
        Tile[][] draw = map.getTiles();

        for (Tile[] tileArr: draw) {
            for (Tile t: tileArr){
                fill(255);
                rect((t.getX()  * tileSize) + tileSize - (tileSize/2),
                        (t.getY()  * tileSize) + tileSize - (tileSize/2),
                        tileSize,
                        tileSize);
                fill(0);
                text("X: " + t.getX(),
                        (t.getX()  * tileSize) + tileSize - (tileSize/2 - (tileSize * 0.2f)),
                        (t.getY()  * tileSize) + tileSize - (tileSize/2) + (tileSize * 0.5f));
                text("Y: " + t.getY(),
                        (t.getX()  * tileSize) + tileSize - (tileSize/2 - (tileSize * 0.2f)),
                        (t.getY()  * tileSize) + tileSize - (tileSize/2) + (tileSize * 0.75f));
            }
        }
    }

    public static void main(String[] args){
        PApplet.main("Main");
    }
}
