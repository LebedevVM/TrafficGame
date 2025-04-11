package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import sun.util.resources.cldr.ext.CurrencyNames_en_BB;

public class PathGraph  implements IndexedGraph<PathNode> {
    PathHeuristic pathHeuristic = new PathHeuristic();
    Array<PathConnection> pathConnections = new Array<>();
    Array<PathNode> nodes = new Array<>();
    ObjectMap<PathNode, Array<Connection<PathNode>>> pathNodeMap = new ObjectMap<>();

    public void addNode (PathNode pathNode) {
        nodes.add(pathNode);
        pathNodeMap.put(pathNode, new Array<>());
    }

    public void addNode (Array<PathNode> pathNodes) {
        for (PathNode pathNode : pathNodes) {
            addNode(pathNode);
        }
    }

    public PathConnection connectNodes (PathNode startNode, PathNode endNode) {
        PathConnection pathConnection = new PathConnection(startNode, endNode);
        pathConnections.add(pathConnection);
        return pathConnection;
    }

    public Array<PathConnection> connectNodes (Array<PathNode> startNodes, Array<PathNode> endNodes) {
        Array<PathConnection> connectedNodes = new Array<>();
        if (startNodes == null || endNodes == null) {
            return null;
        }
        for (PathNode startNode : startNodes) {
            for (PathNode endNode : endNodes) {
                PathConnection pathConnection = new PathConnection(startNode, endNode);
                pathConnections.add(pathConnection);
                connectedNodes.add(pathConnection);
            }
        }
        return connectedNodes;
    }

    public void destroyNodeConnection (Array<PathConnection> pathConnectionsToDestroy) {
        for (PathConnection pathConnectionToDestroy : pathConnectionsToDestroy) {
            pathConnections.removeValue(pathConnectionToDestroy, true);
        }
    }

    public void destroyNode (Array<PathNode> pathNodes) {
        for (PathNode pathNode : pathNodes) {
            nodes.removeValue(pathNode, true);
            pathNodeMap.remove(pathNode);
        }
    }

    public void destroyNode (PathNode pathNode) {
        nodes.removeValue(pathNode, true);
        pathNodeMap.remove(pathNode);
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
