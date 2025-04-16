package proto.traffic.game.map.path.batch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.PathNode;

public class TriplePathNodeBatch extends PathNodeBatch {
    public TriplePathNodeBatch(Vector3 position, PathGraph pathGraph) {
        super(position, pathGraph);

        Vector2 center = new Vector2(position.x, position.z);

        Vector2 leftPoint = new Vector2(position.x - Constants.pathNodeDistance*1f, position.z);
        Vector2 rightPoint = new Vector2(position.x + Constants.pathNodeDistance*1f, position.z);

        PathNode centerNode = new PathNode(new Vector3(center.x, position.y, center.y));

        PathNode left90Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        PathNode right90Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));

        Array<PathNode> path90Nodes = new Array<>();
        path90Nodes.add(left90Node);
        path90Nodes.add(right90Node);
        path90Nodes.add(centerNode);

        leftPoint.rotateAroundDeg(center, 60);
        rightPoint.rotateAroundDeg(center, 60);

        PathNode left150Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        PathNode right150Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));

        Array<PathNode> path150Nodes = new Array<>();
        path150Nodes.add(left150Node);
        path150Nodes.add(right150Node);
        path150Nodes.add(centerNode);

        leftPoint.rotateAroundDeg(center, -120);
        rightPoint.rotateAroundDeg(center, -120);

        PathNode left30Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        PathNode right30Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));

        Array<PathNode> path30Nodes = new Array<>();
        path30Nodes.add(left30Node);
        path30Nodes.add(right30Node);
        path30Nodes.add(centerNode);

        degreesToPathNodes.put(270, path90Nodes);
        degreesToPathNodes.put(90, path90Nodes);

        degreesToPathNodes.put(210, path30Nodes);
        degreesToPathNodes.put(30, path30Nodes);

        degreesToPathNodes.put(150, path150Nodes);
        degreesToPathNodes.put(330, path150Nodes);

        pathGraph.addNode(path90Nodes);
        pathGraph.addNode(path30Nodes);
        pathGraph.addNode(path150Nodes);

        pathConnections.add(pathGraph.connectNodes(right90Node, right30Node));
        pathConnections.add(pathGraph.connectNodes(right150Node, right90Node));
        pathConnections.add(pathGraph.connectNodes(left30Node, right150Node));
        pathConnections.add(pathGraph.connectNodes(left90Node, left30Node));
        pathConnections.add(pathGraph.connectNodes(left150Node, left90Node));
        pathConnections.add(pathGraph.connectNodes(right30Node, left150Node));

        pathConnections.add(pathGraph.connectNodes(centerNode, right30Node));
        pathConnections.add(pathGraph.connectNodes(centerNode, right90Node));
        pathConnections.add(pathGraph.connectNodes(centerNode, right150Node));
        pathConnections.add(pathGraph.connectNodes(centerNode, left30Node));
        pathConnections.add(pathGraph.connectNodes(centerNode, left90Node));
        pathConnections.add(pathGraph.connectNodes(centerNode, left150Node));

        pathConnections.add(pathGraph.connectNodes(right30Node, centerNode));
        pathConnections.add(pathGraph.connectNodes(right90Node, centerNode));
        pathConnections.add(pathGraph.connectNodes(right150Node, centerNode));
        pathConnections.add(pathGraph.connectNodes(left30Node, centerNode));
        pathConnections.add(pathGraph.connectNodes(left90Node, centerNode));
        pathConnections.add(pathGraph.connectNodes(left150Node, centerNode));
    }
}
