public class Inky extends Ghost{
    private Ghost blinky;

    public Inky(int x, int y) {
        super(x, y);
    }

    @Override
    public void Update() {
        super.Update();
    }

    public void setBlinky(Ghost blinky) {
        this.blinky = blinky;
    }
}
