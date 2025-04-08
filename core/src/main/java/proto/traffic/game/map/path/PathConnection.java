package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Connection;

public class PathConnection implements Connection<PathNode> {
    private float cost;
    private final PathNode toNode;
    private final PathNode fromNode;

    public PathConnection (PathNode fromNode, PathNode toNode) {
        this.toNode = toNode;
        this.fromNode = fromNode;

//        cost = toNode.getPosition().dst(fromNode.getPosition());
    }

    public boolean contains (PathNode road) {
        return toNode == road || fromNode == road;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public PathNode getFromNode() {
        return fromNode;
    }

    @Override
    public PathNode getToNode() {
        return toNode;
    }
}
