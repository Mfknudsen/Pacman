public class Player implements Unit {
    private float x, y, x2, y2, size = 30;
    private Tile currentTile, nextMoveTo;
    private float moveSpeed = 0.1f;
    private Direction direction; //2 = Up, 0 = Left, 3 = Down, 1 = Right
    private Direction movingDirection;
    private Direction newDirection;
    private boolean justTeleported = false;

    private boolean wDown = false;
    private boolean aDown = false;
    private boolean sDown = false;
    private boolean dDown = false;
    private boolean moving = false;
    private boolean newPath = false;
    private Tile root;

    public void Update() {
        move();
    }

    public void setSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public void move() {
        if (!moving && direction != null){
            nextMoveTo = getAvailablePath(currentTile, direction);
            moving = true;
            movingDirection = direction;
            direction = null;
        }

        if (moving && direction != null && !newPath){
            int pathDirection = -1;
            if (movingDirection == Direction.UP)
                pathDirection = 2;
            else if (movingDirection == Direction.LEFT)
                pathDirection = 0;
            else if (movingDirection == Direction.DOWN)
                pathDirection = 3;
            else if (movingDirection == Direction.RIGHT)
                pathDirection = 1;

            if(checkAvailablePath(currentTile.getTileNeighbors()[pathDirection], direction))
            {
                x2 = currentTile.getTileNeighbors()[pathDirection].getX();
                y2 = currentTile.getTileNeighbors()[pathDirection].getY();
                newDirection = direction;
                newPath = true;
            }
        } else if (newPath) {
            if (Math.abs(x - x2) <= 0.1f &&
                    Math.abs(y - y2) <= 0.1f) {
                x = x2;
                y = y2;
                nextMoveTo = getAvailablePath(Main.map.getTileFromIndex( (int) x, (int) y), newDirection);
                movingDirection = newDirection;
                direction = null;
                newPath = false;
            }
        }
        if(nextMoveTo != null && moving) {
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
            if (Math.abs(x - nextMoveTo.getX()) <= 0.1f &&
                    Math.abs(y - nextMoveTo.getY()) <= 0.1f) {
                x = nextMoveTo.getX();
                y = nextMoveTo.getY();
                moving = false;
            }
        }
    }

    public void teleportToTile(Tile tile){
        x = tile.getX();
        y = tile.getY();

        setCurrentTile(tile);
        nextMoveTo = null;
        justTeleported = true;
    }

    private boolean checkAvailablePath(Tile currentTile, Direction direction) {

        int pathDirection = -1;
        if (direction == Direction.UP)
            pathDirection = 2;
        else if (direction == Direction.LEFT)
            pathDirection = 0;
        else if (direction == Direction.DOWN)
            pathDirection = 3;
        else if (direction == Direction.RIGHT)
            pathDirection = 1;

        if (currentTile.getTileNeighbors()[pathDirection] != null)
            return currentTile.getTileNeighbors()[pathDirection].getType() != TileType.BLOCKED
                    && currentTile.getTileNeighbors()[pathDirection].getType() != TileType.GhostRoom;
        else return false;
    }

    //region Getters
    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getSize() {
        return (int) size;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public Direction getDirection() {
        return direction;
    }

    private Tile getAvailablePath(Tile currentTile, Direction direction){

        int pathDirection = -1;
        if (direction == Direction.UP)
            pathDirection = 2;
        else if (direction == Direction.LEFT)
            pathDirection = 0;
        else if (direction == Direction.DOWN)
            pathDirection = 3;
        else if (direction == Direction.RIGHT)
            pathDirection = 1;

        root = currentTile;

        if (root.getType() == TileType.PORTAL)
            return root;
        else if (currentTile.getTileNeighbors()[pathDirection].getType() != TileType.BLOCKED
                && currentTile.getTileNeighbors()[pathDirection].getType() != TileType.GhostRoom)
           getAvailablePath(currentTile.getTileNeighbors()[pathDirection], direction);
        return root;
    }



    public boolean getJustTeleported() {
        return justTeleported;

    }

    //endregion

    //region Setters
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //endregion

    void onKeyPressed(char ch) {
        if (ch == 'W' || ch == 'w')
        {
            direction = Direction.UP;
        } else if (ch == 'A' || ch == 'a')
        {
            direction = Direction.LEFT;
        } else if (ch == 'S' || ch == 's')
        {
            direction = Direction.DOWN;
        } else if (ch == 'D' || ch == 'd')
        {
            direction = Direction.RIGHT;
        }
    }

    void onKeyReleased(char ch) {
        if (ch == 'W' || ch == 'w')
        {
            wDown = false;
        } else if (ch == 'A' || ch == 'a')
        {
            aDown = false;
        } else if (ch == 'S' || ch == 's')
        {
            sDown = false;
        } else if (ch == 'D' || ch == 'd')
        {
            dDown = false;
        }
    }
}
