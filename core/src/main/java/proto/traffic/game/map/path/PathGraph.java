package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class PathGraph  implements IndexedGraph<PathNode> {
    PathHeuristic pathHeuristic = new PathHeuristic();
    Array<PathConnection> nodeConnections = new Array<>();
    Array<PathNode> nodes = new Array<>();

    public void addNode (PathNode pathNode) {
        nodes.add(pathNode);
    }

    @Override
    public int getIndex(PathNode node) {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    @Override
    public Array<Connection<PathNode>> getConnections(PathNode fromNode) {
        return null;
    }
}
