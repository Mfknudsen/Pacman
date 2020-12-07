import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
    public static PathNode[] FindPath(Tile start, Tile end){
        List<PathNode> openNodes = new ArrayList<PathNode>();
        List<PathNode> closedNodes = new ArrayList<PathNode>();
        boolean pathFound = false;

        for (PathNode node: CreateNodesFromTile(start)) {
            openNodes.add(node);
        }

        while(!pathFound){
            PathNode node = FindBestNode(openNodes);
        }

        List<PathNode> result = new ArrayList<PathNode>();
        boolean pathMade = false;

        while(!pathMade){

        }

        return result.toArray(new PathNode[result.size()]);
    }

    private static PathNode[] CreateNodesFromTile(Tile input){
        //Used to create the first PathNode in pathing.
        List<PathNode> result = new ArrayList<PathNode>();
        Tile[] neighbors = input.getTileNeighbors();

        for (Tile t: neighbors) {
            if(t != null){
                PathNode node = new PathNode(null, t,0,0);

                result.add(node);
            }
        }

        return result.toArray(new PathNode[result.size()]);
    }

    private static PathNode[] CreateNodesFromPathNode(PathNode input){
        //Used for create PathNode between the first Tile and including the last Tile.
        List<PathNode> result = new ArrayList<PathNode>();
        Tile[] neighbors = input.getTile().getTileNeighbors();

        for (Tile t: neighbors) {
            if(t != null){
                PathNode node = new PathNode(input, t,0,0);

                result.add(node);
            }
        }

        return result.toArray(new PathNode[result.size()]);
    }

    private static PathNode FindBestNode(List<PathNode> input){
        PathNode result = null;

        for (PathNode node: input) {
            if(result == null)
                result = node;
            else if(node.getValue() < result.getValue())
                result = node;
        }

        return result;
    }
}
