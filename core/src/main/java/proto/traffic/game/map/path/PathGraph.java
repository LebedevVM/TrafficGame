package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class PathGraph  implements IndexedGraph<PathNode> {
    PathHeuristic pathHeuristic = new PathHeuristic();
    Array<PathConnection> pathConnections = new Array<>();
    Array<PathNode> nodes = new Array<>();
    ObjectMap<PathNode, Array<Connection<PathNode>>> pathNodeMap = new ObjectMap<>();

    private int lastNodeIndex = 0;

    public GraphPath<PathNode> findPath (PathNode startNode, PathNode goalNode) {
        GraphPath<PathNode> path = new DefaultGraphPath<>();
        if (nodes.contains(startNode, true) && nodes.contains(goalNode, true)) {
            new IndexedAStarPathFinder<>(this).searchNodePath(startNode, goalNode, pathHeuristic, path);
        }
        return path;
    }

    public void addNode (PathNode pathNode) {
        nodes.add(pathNode);
        pathNodeMap.put(pathNode, new Array<>());

        pathNode.setIndex(lastNodeIndex);
        lastNodeIndex ++;
    }

    public void addNode (Array<PathNode> pathNodes) {
        for (PathNode pathNode : pathNodes) {
            addNode(pathNode);
        }
    }

    public PathNode getFirstPathNode () {
        return nodes.first();
    }

    public PathNode getLastPathNode () {
        return nodes.get(lastNodeIndex - 1);
    }

    public PathConnection connectNodes (PathNode startNode, PathNode endNode) {
        PathConnection pathConnection = new PathConnection(startNode, endNode);
        pathConnections.add(pathConnection);
        pathNodeMap.get(startNode).add(pathConnection);
        return pathConnection;
    }

    public void connectBothNodes (PathNode startNode, PathNode endNode, Array<PathConnection> connectedNodes) {
        connectedNodes.add(connectNodes(startNode, endNode));
        connectedNodes.add(connectNodes(endNode, startNode));
    }

    public Array<PathConnection> connectNodes (Array<PathNode> startNodes, Array<PathNode> endNodes) {
        Array<PathConnection> connectedNodes = new Array<>();
        if (startNodes == null || endNodes == null) {
            return null;
        }
        for (PathNode startNode : startNodes) {
            for (PathNode endNode : endNodes) {
                connectedNodes.add(connectNodes(startNode, endNode));
            }
        }
        return connectedNodes;
    }

    public void destroyNodeConnection (Array<PathConnection> pathConnectionsToDestroy) {
        for (PathConnection pathConnectionToDestroy : pathConnectionsToDestroy) {
            pathConnections.removeValue(pathConnectionToDestroy, true);
            Array<Connection<PathNode>> array = pathNodeMap.get(pathConnectionToDestroy.getFromNode());
            if (array != null) {
                array.removeValue(pathConnectionToDestroy, true);
            }
        }
    }

    public void destroyNode (Array<PathNode> pathNodes) {
        for (PathNode pathNode : pathNodes) {
            destroyNode(pathNode);
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
    public int getIndex (PathNode node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount () {
        return lastNodeIndex;
    }

    @Override
    public Array<Connection<PathNode>> getConnections (PathNode fromNode) {
        return pathNodeMap.get(fromNode);
    }
}
