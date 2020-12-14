public class Ghost implements Unit, Scoring{

    // set spawn point (probably x: 11 to 15  and y: 13 to 15)
    // move animation from one tile to the next
    // collision detection, here?

    private float x, y, size = 20;
    private float moveSpeed = 0.05f;
    private Tile nextMoveTo;
    private Pathfinder pathfinder;
    private GhostState state;
    private Tile current, scatter;
    private Player target;
    private PathNode[] currentPath;


    public Ghost(int x, int y) {
        pathfinder = new Pathfinder();
        state = GhostState.CHASE;

        this.x = x;
        this.y = y;
    }

    public void Update(){
        switch (state) {
            case WAIT:
                break;

            case CHASE:
                pathfinder.setEndTile(target.getCurrentTile());
                break;

            case FLEE:
                break;

            case RETURN:
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
                if(current != null && target != null) {
                    pathfinder.setStartTile(current);
                    currentPath = pathfinder.FindPath();

                    if (currentPath.length > 0)
                        if (currentPath.length > 0)
                            nextMoveTo = currentPath[currentPath.length - 2].getTile();
                }
            }
        }
    }

    private void move() {
        if(nextMoveTo != null) {
            if (x != nextMoveTo.getX()) {
                int dir = -1;
                if (x < nextMoveTo.getX())
                    dir = 1;
                x += dir * moveSpeed;

            } else if (y != nextMoveTo.getY()) {
                int dir = -1;
                if (y < nextMoveTo.getY())
                    dir = 1;
                y += dir * moveSpeed;
            }
        }
    }

    private void updateTarget(){

    }

    public void collision() {

    }

    //region Setters
    public void setTarget(Player target) {
        if (target != null) {
            this.target = target;
        }
    }

    public void setCurrent(Tile current) {
        this.current = current;
    }

    public void setState(GhostState state){
        this.state = state;
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
    //endregion
}
