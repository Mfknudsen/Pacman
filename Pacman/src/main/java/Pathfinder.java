import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
    private Tile startTile, endTile;

    public List<PathNode> openNodes = new ArrayList<PathNode>();
    public List<PathNode> closedNodes = new ArrayList<PathNode>();
    public List<PathNode> result = new ArrayList<PathNode>();

    public PathNode[] FindPath(){
        openNodes = new ArrayList<PathNode>();
        closedNodes = new ArrayList<PathNode>();
        result = new ArrayList<PathNode>();
        boolean pathFound = false;
        boolean pathMade = false;

        //Start the pathfinding from the current tile
        openNodes.add(CreateNodesFromTile(startTile));

        //Search the open Nodes until non is left or if a PathNode is create with the end tile
        while(!pathFound && (openNodes.size() > 0)) {
            PathNode node = FindBestNode(openNodes);

            if (node != null) {
                for (PathNode addition : CreateNodesFromPathNode(node)) {
                    openNodes.add(addition);

                    if (addition.getTile() == endTile) {
                        pathFound = true;
                        result.add(addition);
                    }
                }

                closedNodes.add(node);
                openNodes.remove(node);
            }
        }

        //Backtrace using parents in the PathNodes
        while(!pathMade && (result.size() > 0)){
            PathNode node = result.get(result.size() - 1);
            if(node.getTile() != startTile){
                result.add(node.parent);
            }
            else
                pathMade = true;
        }
        return result.toArray(new PathNode[result.size()]);
    }

    private PathNode CreateNodesFromTile(Tile input){
        //Used to create the first PathNode in pathing.
        PathNode result = new PathNode(null, input);
        return result;
    }

    private PathNode[] CreateNodesFromPathNode(PathNode input){
        //Used for create PathNode between the first Tile and including the last Tile.
        List<PathNode> result = new ArrayList<PathNode>();
        Tile[] neighbors = input.getTile().getTileNeighbors();

        for (Tile t: neighbors) {
            if(t != null && endTile != null) {
                if (t.getType() == TileType.WALKABLE && NodeDontExitsInClosed(t)) {
                    PathNode node = new PathNode(input, t);
                    node.setValue(endTile.getX(), endTile.getY());
                    result.add(node);
                }
            }
        }

        return result.toArray(new PathNode[result.size()]);
    }

    private PathNode FindBestNode(List<PathNode> input){
        PathNode result = input.get(0);

        for (PathNode node: input) {
            if(node.getValue() < result.getValue())
                result = node;
        }

        return result;
    }

    private Boolean NodeDontExitsInClosed(Tile input){
        int x = input.getX(), y = input.getY();

        for (PathNode node: closedNodes){
            if(node.getTile().getX() == x && node.getTile().getY() == y)
                return false;
        }

        return true;
    }

    //region Setters
    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public void setEndTile(Tile endTile) {
        this.endTile = endTile;
    }
    //endregion
}
