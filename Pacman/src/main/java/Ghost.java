public class Ghost implements Unit, Scoring{

    // set spawn point (probably x: 11 to 15  and y: 13 to 15)
    // move animation from one tile to the next
    // collision detection, here?

    private int x;
    private int y;
    private float moveSpeed;
    private Tile nextMoveTo;
    private Pathfinder pathfinder;

    public Ghost() {
        pathfinder = new Pathfinder();
    }

    public void Update(){
        if(nextMoveTo == null){
            nextMoveTo = pathfinder.FindPath()[0].getTile();
        }
        else{
            move();
        }
    }

    public void setSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if(x != nextMoveTo.getX()){
            int dir = -1;
            if(x < nextMoveTo.getX())
                dir = 1;
            x += dir * moveSpeed;

        } else if(y != nextMoveTo.getY()){
            int dir = -1;
            if(y < nextMoveTo.getY())
                dir = 1;
            y += dir * moveSpeed;
        }

        if((nextMoveTo.getX() - x) < 0.1f && (nextMoveTo.getY() - y) < 0.1f){
            nextMoveTo = null;
        }
    }

    private void updateTarget(){

    }

    public void collision() {

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int addScore() {
        return 0;
    }
}
