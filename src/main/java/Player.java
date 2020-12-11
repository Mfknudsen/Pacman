import java.awt.event.KeyEvent;

public class Player implements Unit {

    // set spawn point (probably x: 13 or 14 and y:23)
    // move animation from one tile to the next
    // collision detection, here?

    private int x;
    private int y;

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
        if (aDown && !dDown)
        {
            moveLeft();
        }
        if (sDown && !wDown)
        {
            moveDown();
        }
        if (dDown && !aDown)
        {
            moveRight();
        }
    }

    public void setSpawnPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {

    }

    public void collision() {

    }

    private void moveLeft() {
        if ((x - 1) < 0) {
            return;
        }
        --x;
    }

    private void moveRight() {
        if ((x + 1) > Main.xSize - 1) {
        return;
        }
        ++x;
    }

    private void moveUp() {
        if ((y - 1) < 0) {
        return;
        }
        --y;
    }

    private void moveDown() {
        if ((y + 1) > Main.ySize - 1) {
            return;
        }
        ++y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    void onKeyPressed(char ch)
    {
        if (ch == 'W' || ch == 'w')
        {
            wDown = true;
        } else if (ch == 'A' || ch == 'a')
        {
            aDown = true;
        } else if (ch == 'S' || ch == 's')
        {
            sDown = true;
        } else if (ch == 'D' || ch == 'd')
        {
            dDown = true;
        }
    }

    void onKeyReleased(char ch)
    {
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
