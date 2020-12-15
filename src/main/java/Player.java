public class Player extends Map implements Unit {
    private float x, y, size = 30;
    private Tile currentTile, nextMoveTo;
    private float moveSpeed = 0.1f;
    private Direction direction; //2 = Up, 0 = Left, 3 = Down, 1 = Right

    private boolean wDown = false;
    private boolean aDown = false;
    private boolean sDown = false;
    private boolean dDown = false;
    private boolean moving = false;

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
            direction = null;
        }

        if (moving && direction != null){
            if(checkAvailablePath(currentTile, direction))
            {
                nextMoveTo = getAvailablePath(currentTile, direction);
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



    private void moveLeft() {
        direction = Direction.LEFT;

        nextMoveTo = getAvailablePath(currentTile, direction);

        if(x == nextMoveTo.getX() && y == nextMoveTo.getY()){
            aDown = false;
            nextMoveTo = null;
        }

        if(nextMoveTo != null) {
            move();
        }
    }

    private void moveRight() {
        direction = Direction.RIGHT;

        nextMoveTo = getAvailablePath(currentTile, direction);

        if(x == nextMoveTo.getX() && y == nextMoveTo.getY()) {
            dDown = false;
            nextMoveTo = null;
        }

        if(nextMoveTo != null) {
            move();
        }
    }

    private void moveUp() {
        direction = Direction.UP;

        nextMoveTo = getAvailablePath(currentTile, direction);

        if(x == nextMoveTo.getX() && y == nextMoveTo.getY()) {
            wDown = false;
            nextMoveTo = null;
        }

        if(nextMoveTo != null) {
            move();
        }
    }

    private void moveDown() {
        direction = Direction.DOWN;

        nextMoveTo = getAvailablePath(currentTile, direction);

        if(x == nextMoveTo.getX() && y == nextMoveTo.getY()) {
            sDown = false;
            nextMoveTo = null;
        }

        if(nextMoveTo != null) {
            move();
        }
    }

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

        if (currentTile.getTileNeighbors()[pathDirection].getType() != TileType.BLOCKED
                && currentTile.getTileNeighbors()[pathDirection].getType() != TileType.GhostRoom)
            return currentTile.getTileNeighbors()[pathDirection];
        return null;
    }

    private boolean checkAvailablePath(Tile currentTile, Direction direction){

        int pathDirection = -1;
        if (direction == Direction.UP)
            pathDirection = 2;
        else if (direction == Direction.LEFT)
            pathDirection = 0;
        else if (direction == Direction.DOWN)
            pathDirection = 3;
        else if (direction == Direction.RIGHT)
            pathDirection = 1;

        return currentTile.getTileNeighbors()[pathDirection].getType() != TileType.BLOCKED
                && currentTile.getTileNeighbors()[pathDirection].getType() != TileType.GhostRoom;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

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
