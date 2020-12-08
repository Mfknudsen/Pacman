import processing.core.PApplet;

public class Main extends PApplet {
    private Map map;
    public static int size = 40, tileSize = 28;
    public static int xSize = 28, ySize = 31;
    public static int gameMapColorVCX = 87;

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

                //region outer walls
                if (
                             (t.getX() <= 28 && (t.getY() == 0 || t.getY() == 30)) // top and bottom
                        || ( (t.getX() == 0 || t.getX() == xSize - 1 ) && (t.getY() <= 9 || (t.getY() <= 30 && t.getY() >= 20) ) ) // sides
                        || ( (t.getX() <= 5 || (t.getX() >= xSize - 6 && t.getX() <= xSize - 1)) && (t.getY() == 9 || t.getY() == 13 || t.getY() == 15 || t.getY() == 19)) // tubes
                        || ( (t.getX() == 5 || t.getX() == 22) && ( (t.getY() <= 12 && t.getY() >= 10) || (t.getY() <= 18 && t.getY() >= 16 ))) // tube sides
                    )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region top middle block
                else if (
                        ( (t.getX() >= 13 && t.getX() <= 14) && (t.getY() <= 4 && t.getY() >= 1) )
                )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region top rectangles
                else if (
                        ( (t.getX() >= 2) && (t.getX() <= 5) || (t.getX() >= 7) && (t.getX() <= 11) || (t.getX() >= 16) && (t.getX() <= 20) || (t.getX() >= 22) && (t.getX() <= 25))
                        && (t.getY() >= 2 && t.getY() <= 4))
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region second set rectangles below top rectangles
                else if (
                        ( (t.getX() >= 2) && (t.getX() <= 5) ||(t.getX() >= 22) && (t.getX() <= 25))
                                && (t.getY() >= 6 && t.getY() <= 7))
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region top t-blocks
                else if (
                          ( ((t.getX() >=7  && t.getX() <= 8)  && (t.getY() >=6 && t.getY() <= 13)) || ((t.getX() >=7  && t.getX() <= 11) && (t.getY() >=9 && t.getY() <= 10)) ) //T-block left
                       || ( ((t.getX() >=10 && t.getX() <= 17) && (t.getY() >=6 && t.getY() <= 7 )) || ((t.getX() >=13 && t.getX() <= 14) && (t.getY() >=8 && t.getY() <= 10)) ) //T-block middle
                       || ( ((t.getX() >=19 && t.getX() <= 20) && (t.getY() >=6 && t.getY() <= 13)) || ((t.getX() >=16 && t.getX() <= 20) && (t.getY() >=9 && t.getY() <= 10)) ) // T-block right
                        )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region vertical rectangles
                else if ( (t.getX() >=7  && t.getX() <= 8) && (t.getY() >=15 && t.getY() <= 19)
                        ||(t.getX() >=19  && t.getX() <= 20) && (t.getY() >=15 && t.getY() <= 19))
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region ghost box
                else if (
                           ((t.getX() >=10  && t.getX() <= 12) || (t.getX() >=15  && t.getX() <= 17)) && (t.getY()  == 12)
                        || (t.getX() == 10  || t.getX() == 17) && (t.getY() >=12 && t.getY() <= 16)
                        || ((t.getX() >=10  && t.getX() <= 17) && (t.getY()  == 16))
                        )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region middle T-block (under ghost box)
                else if (
                        ((t.getX() >=10  && t.getX() <= 17) && (t.getY() >=18 && t.getY() <= 19)
                                || (t.getX() >=13  && t.getX() <= 14) && (t.getY() >=20 && t.getY() <= 22))
                )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region L shapes
                else if (
                        (((t.getX() >=2  && t.getX() <= 5)|| (t.getX() >=22  && t.getX() <= 25)) && (t.getY() >= 21 && t.getY() <= 22)
                                || ((t.getX() >=4  && t.getX() <= 5)|| (t.getX() >=22  && t.getX() <= 23)) && (t.getY() >=23 && t.getY() <= 24))
                )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region Bottom rectangles
                else if (
                        (((t.getX() >=7  && t.getX() <= 11)|| (t.getX() >= 16 && t.getX() <= 20)) && (t.getY() >= 21 && t.getY() <= 22))
                )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region Bottom T-shapes
                else if (
                            ( (t.getX() >= 2  && t.getX() <= 11) && (t.getY() >= 27 && t.getY() <= 28) )
                                    || ( (t.getX() >= 7 && t.getX() <= 8) && (t.getY() >= 25 && t.getY() <= 28) ) // Left T-shape

                        ||  ( (t.getX() >= 10 && t.getX() <= 17) && (t.getY() >= 24 && t.getY() <= 25) )
                                    || ( (t.getX() >= 13 && t.getX() <= 14) && (t.getY() >= 26 && t.getY() <= 28) )// Middle T-shape

                        ||  (((t.getX() >= 16 && t.getX() <= 25)) && (t.getY() >= 27 && t.getY() <= 28))
                                    || ( (t.getX() >= 19 && t.getX() <= 20) && (t.getY() >= 25 && t.getY() <= 28) )// Right T-shape
                )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                //region Bottom T-shapes
                else if (
                        ( (t.getX() >= 1  && t.getX() <= 2) && (t.getY() >= 24 && t.getY() <= 25) ) // Left bump
                                || ( (t.getX() >= xSize - 3 && t.getX() <= xSize - 2) && (t.getY() >= 24 && t.getY() <= 25) ) // Right bump

                )
                {
                    fill(gameMapColorVCX);
                }
                //endregion

                else
                {
                fill(255);
                }
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
