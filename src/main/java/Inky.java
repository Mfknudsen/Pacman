import java.util.ArrayList;
import java.util.List;

public class Inky extends Ghost{
    private Ghost blinky;

    public Inky(int x, int y) {
        super(x, y);
    }

    public void setBlinky(Ghost blinky) {
        this.blinky = blinky;
    }

    @Override
    protected void determineTarget(Tile preDetermine) {
        Tile result = preDetermine;

        if(state == GhostState.CHASE){
            int xTarget, yTarget;
            int xDir, yDir;
            double dist;

            xTarget = (int) target.getX() - 1;
            yTarget = (int) target.getY();

            if(target.getDirection() == Direction.LEFT)
                xTarget += -1;
            else if(target.getDirection() == Direction.RIGHT)
                xTarget += 1;
            else if(target.getDirection() == Direction.DOWN)
                yTarget += 1;
            else
                yTarget += -1;

            xDir = (int) blinky.getX() - xTarget;
            yDir = (int) blinky.getY() - yTarget;

            xTarget -= xDir;
            yTarget -= yDir;

            if(xTarget < 0)
                xTarget = 0;
            else if(xTarget >= Main.xSize)
                xTarget = Main.xSize - 1;

            if(yTarget < 0)
                yTarget = 0;
            else if(yTarget >= Main.ySize)
                yTarget = Main.ySize - 1;

            Tile newCurrent = Main.map.getTileFromIndex(xTarget, yTarget);
            List<Tile> arr = new ArrayList<Tile>();
            result = findClosesWalkableTile(newCurrent, arr);
        }

        super.determineTarget(result);
    }

    private Tile findClosesWalkableTile(Tile current, List<Tile> toCheck){
        Tile result;
        List<Tile> arr = new ArrayList<Tile>();

        for (Tile t: toCheck) {
            arr.add(t);
        }

        if(current.getType() != TileType.BLOCKED)
            result = current;
        else{
            for (Tile t: current.getTileNeighbors()) {
                if(!arr.contains(t))
                    arr.add(t);
            }

            Tile newCurrent = null;
            for (int i = 0; i < arr.size(); i++){
                if(arr.get(i) != null && newCurrent == null){
                    newCurrent = arr.get(i);
                    break;
                }
            }
            if(arr.size() > 0)
            arr.remove(0);
            result = findClosesWalkableTile(newCurrent, arr);
        }

        return result;
    }
}
