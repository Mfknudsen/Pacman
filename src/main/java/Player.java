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

    public void moveLeft()
    {
        --x;
    }

    public void moveRight()
    {
        ++x;
    }

    public void moveUp()
    {
        --y;
    }

    public void moveDown()
    {
        ++y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
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
