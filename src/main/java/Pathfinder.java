import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
    private Tile startTile, endTile, preTile;

    public List<PathNode> openNodes = new ArrayList<PathNode>();
    public List<PathNode> closedNodes = new ArrayList<PathNode>();
    public List<PathNode> result = new ArrayList<PathNode>();

    public PathNode[] FindPath() {
        if(startTile == null || endTile == null)
            return new PathNode[0];

        openNodes = new ArrayList<PathNode>();
        closedNodes = new ArrayList<PathNode>();
        result = new ArrayList<PathNode>();
        boolean pathFound = false;
        boolean pathMade = false;

        //Start the pathfinding from the current tile
        for (PathNode node: CreateNodesFromTile(startTile)) {
            openNodes.add(node);
        }

        //Search the open Nodes until non is left or if a PathNode is create with the end tile
        while (!pathFound && (openNodes.size() > 0)) {
            PathNode node = FindBestNode(openNodes);

            if (node != null) {
                if(node.getTile() != endTile) {
                    for (PathNode addition : CreateNodesFromPathNode(node)) {
                        openNodes.add(addition);

                        if (addition.getTile() == endTile) {
                            pathFound = true;
                            result.add(addition);
                        }
                    }
                }
                else{
                    pathFound = true;
                    result.add(node);
                }

                closedNodes.add(node);
                openNodes.remove(node);
            }
        }

        //Backtrace using parents in the PathNodes
        while (!pathMade && (result.size() > 0)) {
            PathNode node = result.get(result.size() - 1);
            if (node.parent != null)
                result.add(node.parent);
            else
                pathMade = true;
        }

        if(result.size() == 0){
            Tile t = randomNeighborTile(startTile);
            PathNode node = new PathNode(null, t);
            result.add(node);
        }

        return result.toArray(new PathNode[result.size()]);
    }

    private PathNode[] CreateNodesFromTile(Tile input) {
        //Used to create the first PathNode in pathing.
        List<PathNode> result = new ArrayList<PathNode>();

        for (Tile t: input.getTileNeighbors()) {
            if(t != null){
                if(preTile != null){
                    if(t.getX() == preTile.getX() && t.getY() == preTile.getY())
                        continue;
                }

                if(t.getType() != TileType.BLOCKED){
                    result.add(new PathNode(null, t));
                }
            }
        }

        return result.toArray(new PathNode[result.size()]);
    }

    private PathNode[] CreateNodesFromPathNode(PathNode input) {
        //Used for create PathNode between the first Tile and including the last Tile.
        List<PathNode> result = new ArrayList<PathNode>();
        Tile[] neighbors = input.getTile().getTileNeighbors();

        for (Tile t : neighbors) {
            if(t != null) {
                if(t.getX() == input.getTile().getX() && t.getY() == input.getTile().getY()) {
                    System.out.println("Parent");
                    continue;
                }

                if (preTile != null) {
                    if (t.getX() == preTile.getX() && t.getY() == preTile.getY()) {
                        continue;
                    }
                }

                if(t.getX() == startTile.getX() && t.getY() == startTile.getY())
                    continue;

                if (endTile != null) {
                    if ((t.getType() != TileType.BLOCKED) && NodeDontExitsInClosed(t)) {
                        PathNode node = new PathNode(input, t);
                        node.setValue(endTile.getX(), endTile.getY());
                        node.setParent(FindBestParent(node));
                        if(node.parent != null)
                            node.setN(node.parent.getN() + 1);
                        else
                            node.setN(0);
                        result.add(node);
                    }
                }
            }
        }
        return result.toArray(new PathNode[result.size()]);
    }

    private PathNode FindBestNode(List<PathNode> input){
        PathNode result = input.get(0);

        for (PathNode node: input) {
                if (node.getValue() < result.getValue())
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

    private PathNode FindBestParent(PathNode input){
        PathNode result = input.parent;

        List<PathNode> toSearch = new ArrayList<PathNode>();
        toSearch.addAll(closedNodes);
        toSearch.addAll(openNodes);

        for (PathNode node: toSearch) {
            boolean check = false;

            for (Tile t: input.getTile().getTileNeighbors()) {
                if (t != null) {
                    if (t.getX() == node.getTile().getX() && t.getY() == node.getTile().getY()){
                        check = true;
                        break;
                    }
                }
            }

            if(node.getN() < result.getN() && check)
                result = node;
        }

        for (Tile t: startTile.getTileNeighbors()) {
            if (t != null) {
                if (t.getX() == input.getTile().getX() && t.getY() == input.getTile().getY()) {
                    result = null;
                    break;
                }
            }
        }

        return result;
    }

    public Tile randomNeighborTile(Tile input){
        Tile result;
        List<Tile> arr = new ArrayList<Tile>();

        for (Tile t: input.getTileNeighbors()) {
            if(t != null) {
                if (t.getType() != TileType.BLOCKED){
                    if(preTile != null) {
                        if (t.getX() == getPreTile().getX() && t.getY() == getPreTile().getY())
                            continue;
                    }

                    arr.add(t);
                }
            }
        }

        int index = (int) (Math.random() * 3);
        if(index >= arr.size())
            index = arr.size() - 1;
        if(index < 0)
            index = 0;
        result = arr.get(index);

        return result;
    }

    //region Setters
    public void setStartTile(Tile startTile) {
        if(startTile != this.startTile) {
            if(this.startTile != null)
                preTile = this.startTile;
            this.startTile = startTile;
        }
    }

    public void setEndTile(Tile endTile) {
        this.endTile = endTile;
    }
    //endregion

    //region Getters
    public Tile getPreTile() {
        return preTile;
    }

    public List<PathNode> getClosedNodes() {
        return closedNodes;
    }
    //endregion
}
