public class Pinky extends Ghost{
    public Pinky(int x, int y) {
        super(x, y);
    }

    @Override
    protected void determineTarget(Tile preDetermine) {
        Tile result = preDetermine;
        if(preDetermine == target.getCurrentTile()){
            if(target.getDirection() == Direction.UP){
                if(target.getCurrentTile().getTileNeighbors()[2].getType() == TileType.BLOCKED)
                    target.setDirection(Direction.LEFT);
                else{
                    result = target.getCurrentTile().getTileNeighbors()[2];

                    if(result.getTileNeighbors()[2].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[2];
                    else if(result.getTileNeighbors()[0].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[0];
                    else if(result.getTileNeighbors()[1].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[1];
                }
            }
            else if(target.getDirection() == Direction.DOWN){
                if(target.getCurrentTile().getTileNeighbors()[3].getType() == TileType.BLOCKED)
                    target.setDirection(Direction.RIGHT);
                else{
                    result = target.getCurrentTile().getTileNeighbors()[3];

                    if(result.getTileNeighbors()[3].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[3];
                    else if(result.getTileNeighbors()[1].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[1];
                    else if(result.getTileNeighbors()[0].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[0];
                }
            }
            else if(target.getDirection() == Direction.LEFT){
                if(target.getCurrentTile().getTileNeighbors()[0].getType() == TileType.BLOCKED)
                    target.setDirection(Direction.DOWN);
                else{
                    result = target.getCurrentTile().getTileNeighbors()[0];

                    if(result.getTileNeighbors()[0].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[0];
                    else if(result.getTileNeighbors()[3].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[3];
                    else if(result.getTileNeighbors()[2].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[2];
                }
            }
            else{
                if(target.getCurrentTile().getTileNeighbors()[1].getType() == TileType.BLOCKED)
                    target.setDirection(Direction.UP);
                else{
                    result = target.getCurrentTile().getTileNeighbors()[1];

                    if(result.getTileNeighbors()[1].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[1];
                    else if(result.getTileNeighbors()[2].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[2];
                    else if(result.getTileNeighbors()[3].getType() != TileType.BLOCKED)
                        result = result.getTileNeighbors()[3];
                }
            }
        }

        super.determineTarget(result);
    }
}
