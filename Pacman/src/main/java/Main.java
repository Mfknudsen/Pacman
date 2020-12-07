import processing.core.PApplet;

public class Main extends PApplet {
    private Map map;
    public static int size = 40, tileSize = 40;
    public static int xSize = 20, ySize = 15;

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
                rect(t.getX() - (tileSize/2), t.getY() - (tileSize/2), tileSize, tileSize);
            }
        }
    }

    public static void main(String[] args){
        PApplet.main("Main");
    }
}
