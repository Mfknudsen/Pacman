import java.util.ArrayList;
import java.util.List;

public class Ghost implements Unit{
    protected float x, y, size = 30;
    protected float moveSpeed = 0.05f;
    protected float r,g,b;
    protected Tile nextMoveTo;
    public Pathfinder pathfinder;
    protected GhostState state;
    protected Tile current, scatter, spawn;
    protected Player target;
    protected PathNode[] currentPath;
    protected Direction direction;

    //Wait timer
    float timer, delayTime;

    public Ghost(int x, int y, float delayTime) {
        pathfinder = new Pathfinder();
        state = GhostState.WAIT;
        direction = Direction.DOWN;
        spawn = Main.map.getTileFromIndex(x,y);
        this.x = x;
        this.y = y;
        this.delayTime = delayTime * 120;
    }

    public void Update(){
        switch (state) {
            case WAIT:
                if(timer >= delayTime)
                    state = GhostState.CHASE;
                else
                    timer += 0.1f;
                break;

            case CHASE:
                determineTarget(target.getCurrentTile());
                break;

            case SCATTER:
                determineTarget(scatter);
                if(current.getX() == scatter.getX() && current.getY() == scatter.getY())
                    state = GhostState.CHASE;
                break;

            case FLEE:
                determineTarget(pathfinder.randomNeighborTile(current));
                break;

            case RETURN:
                determineTarget(spawn);
                if(current.getX() == spawn.getX() && current.getY() == spawn.getY())
                    state = GhostState.CHASE;
                break;
        }

        if(state != GhostState.WAIT) {
            if(nextMoveTo != null) {
                move();

                if (Math.abs(x - nextMoveTo.getX()) <= 0.1f &&
                        Math.abs(y - nextMoveTo.getY()) <= 0.1f) {
                    x = nextMoveTo.getX();
                    y = nextMoveTo.getY();
                    nextMoveTo = null;
                }
            }
            else {
                if(current != null) {
                    currentPath = pathfinder.FindPath();

                    if (currentPath.length > 0)
                        nextMoveTo = currentPath[currentPath.length - 1].getTile();
                }
            }
        }
    }

    private void move() {
        if(nextMoveTo != null) {
            if (x != nextMoveTo.getX()) {
                int dir = -1;
                direction = Direction.LEFT;
                if (x < nextMoveTo.getX()) {
                    dir = 1;
                    direction = Direction.RIGHT;
                }
                x += dir * moveSpeed;

            } else if (y != nextMoveTo.getY()) {
                int dir = -1;
                direction = Direction.UP;
                if (y < nextMoveTo.getY()) {
                    dir = 1;
                    direction = Direction.DOWN;
                }
                y += dir * moveSpeed;
            }
        }
    }

    protected void determineTarget(Tile preDetermine){
        pathfinder.setEndTile(preDetermine);
    }
    //region Setters
    public void setTarget(Player target) {
        if (target != null) {
            this.target = target;
        }
    }

    public void setCurrent(Tile current) {
        this.current = current;

        pathfinder.setStartTile(this.current);
    }

    public void setScatter(Tile scatter) {
        this.scatter = scatter;
    }

    public void setState(GhostState state){
        this.state = state;
    }

    public void setColor(float r, float b, float g){
        this.r = r;
        this.b = b;
        this.g = g;
    }
    //endregion

    //region Getters
    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int addScore() {
        return 0;
    }

    public int getSize() {
        return (int) size;
    }

    public Direction getDirection() {
        return direction;
    }

    public float[] getColor(){
        return new float[]{r,g,b};
    }

    public GhostState getState() {
        return state;
    }
    //endregion
}
