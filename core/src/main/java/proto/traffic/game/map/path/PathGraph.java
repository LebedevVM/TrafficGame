package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import sun.util.resources.cldr.ext.CurrencyNames_en_BB;

public class PathGraph  implements IndexedGraph<PathNode> {
    PathHeuristic pathHeuristic = new PathHeuristic();
    Array<PathConnection> pathConnections = new Array<>();
    Array<PathNode> nodes = new Array<>();

    public void addNode (PathNode pathNode) {
        nodes.add(pathNode);
    }

    public void addNode (Array<PathNode> pathNodes) {
        for (PathNode pathNode : pathNodes) {
            addNode(pathNode);
        }
    }

    public void connectNodes (PathNode startNode, PathNode endNode) {
        PathConnection pathConnection = new PathConnection(startNode, endNode);
        pathConnections.add(pathConnection);
    }

    public void connectNodes (Array<PathNode> startNodes, Array<PathNode> endNodes) {
        if (startNodes == null || endNodes == null) {
            return;
        }
        for (PathNode startNode : startNodes) {
            for (PathNode endNode : endNodes) {
                PathConnection pathConnection = new PathConnection(startNode, endNode);
                pathConnections.add(pathConnection);
            }
        }
    }

    public void show (ModelBatch batch, Environment environment) {
        for (PathConnection pathConnection : pathConnections) {
            pathConnection.show(batch, environment);
        }
    }

    @Override
    public int getIndex(PathNode node) {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    @Override
    public Array<Connection<PathNode>> getConnections(PathNode fromNode) {
        return null;
    }
}
