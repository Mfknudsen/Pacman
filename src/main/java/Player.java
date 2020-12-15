import java.awt.event.KeyEvent;

public class Player extends Map implements Unit {
    private float x, y, size = 30;
    private Tile currentTile, nextMoveTo;
    private float moveSpeed = 0.1f;
    private Direction direction; //2 = Up, 0 = Left, 3 = Down, 1 = Right

    private boolean wDown = false;
    private boolean aDown = false;
    private boolean sDown = false;
    private boolean dDown = false;
    private boolean arrowUpIsDown = false;
    private boolean arrowLeftIsDown = false;
    private boolean arrowDownIsDown = false;
    private boolean arrowRightIsDown = false;

    public void Update() {
        if (wDown && !sDown)
        {
            moveUp();
        }
         else if (aDown && !dDown)
        {
            moveLeft();
        }
        else if (sDown && !wDown)
        {
            moveDown();
        }
        else if (dDown && !aDown)
        {
            moveRight();
        }
    }

    public void setSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public void move() {
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
        if (Math.abs(x - nextMoveTo.getX()) <= 0.1f &&
                Math.abs(y - nextMoveTo.getY()) <= 0.1f) {
            x = nextMoveTo.getX();
            y = nextMoveTo.getY();
            nextMoveTo = null;
        }
    }
    
    private void moveLeft() {
        direction = Direction.LEFT;

        nextMoveTo = currentTile.getBlockedOrPortal(currentTile, 0);

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

        nextMoveTo = currentTile.getBlockedOrPortal(currentTile, 1);

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

        nextMoveTo = currentTile.getBlockedOrPortal(currentTile, 2);

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

        nextMoveTo = currentTile.getBlockedOrPortal(currentTile, 3);

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
            wDown = true;
            sDown = false;
            aDown = false;
            dDown = false;
        } else if (ch == 'A' || ch == 'a')
        {
            wDown = false;
            sDown = false;
            aDown = true;
            dDown = false;
        } else if (ch == 'S' || ch == 's')
        {
            wDown = false;
            sDown = true;
            aDown = false;
            dDown = false;
        } else if (ch == 'D' || ch == 'd')
        {
            wDown = false;
            sDown = false;
            aDown = false;
            dDown = true;
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
