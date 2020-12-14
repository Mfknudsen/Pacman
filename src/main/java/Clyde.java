public class Clyde extends Ghost{
    public Clyde(int x, int y, float delayTime) {
        super(x, y, delayTime);
    }

    @Override
    protected void determineTarget(Tile preDetermine) {
        Tile result = preDetermine;
        if(state == GhostState.CHASE){
            if(Math.sqrt(
                    Math.pow(target.getX() - x, 2) + Math.pow(target.getY() - y, 2))
                    <= 5 ){
                result = scatter;
            }
        }


        super.determineTarget(result);
    }
}
