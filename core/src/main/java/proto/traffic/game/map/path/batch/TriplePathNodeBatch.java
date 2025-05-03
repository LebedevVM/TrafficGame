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

        Vector2 centerPoint = new Vector2(position.x, position.z - Constants.pathNodeRadius * 2f);
        Vector2 leftPoint = new Vector2(centerPoint.x + Constants.pathNodeRadius/1.5f, centerPoint.y);
        Vector2 rightPoint = new Vector2(centerPoint.x - Constants.pathNodeRadius/1.5f, centerPoint.y);

        PathNode[] centerPathNodes = new PathNode[6];
        PathNode[] leftPathNodes = new PathNode[6];
        PathNode[] rightPathNodes = new PathNode[6];

        PathNode center0Node = new PathNode(new Vector3(centerPoint.x, position.y, centerPoint.y));
        Array<PathNode> path0Nodes = new Array<>();
        path0Nodes.add(center0Node);

        centerPathNodes[0] = center0Node;

        centerPoint.rotateAroundDeg(center, -60);

        PathNode center60Node = new PathNode(new Vector3(centerPoint.x, position.y, centerPoint.y));
        Array<PathNode> path60Nodes = new Array<>();
        path60Nodes.add(center60Node);

        centerPathNodes[1] = center60Node;

        centerPoint.rotateAroundDeg(center, -60);

        PathNode center120Node = new PathNode(new Vector3(centerPoint.x, position.y, centerPoint.y));
        Array<PathNode> path120Nodes = new Array<>();
        path120Nodes.add(center120Node);

        centerPathNodes[2] = center120Node;

        centerPoint.rotateAroundDeg(center, -60);

        PathNode center180Node = new PathNode(new Vector3(centerPoint.x, position.y, centerPoint.y));
        Array<PathNode> path180Nodes = new Array<>();
        path180Nodes.add(center180Node);

        centerPathNodes[3] = center180Node;

        centerPoint.rotateAroundDeg(center, -60);

        PathNode center240Node = new PathNode(new Vector3(centerPoint.x, position.y, centerPoint.y));
        Array<PathNode> path240Nodes = new Array<>();
        path240Nodes.add(center240Node);

        centerPathNodes[4] = center240Node;

        centerPoint.rotateAroundDeg(center, -60);

        PathNode center300Node = new PathNode(new Vector3(centerPoint.x, position.y, centerPoint.y));
        Array<PathNode> path300Nodes = new Array<>();
        path300Nodes.add(center300Node);

        centerPathNodes[5] = center300Node;

        PathNode left0Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        path0Nodes.add(left0Node);

        leftPathNodes[0] = left0Node;

        leftPoint.rotateAroundDeg(center, -60);

        PathNode left60Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        path60Nodes.add(left60Node);

        leftPathNodes[1] = left60Node;

        leftPoint.rotateAroundDeg(center, -60);

        PathNode left120Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        path120Nodes.add(left120Node);

        leftPathNodes[2] = left120Node;

        leftPoint.rotateAroundDeg(center, -60);

        PathNode left180Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        path180Nodes.add(left180Node);

        leftPathNodes[3] = left180Node;

        leftPoint.rotateAroundDeg(center, -60);

        PathNode left240Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        path240Nodes.add(left240Node);

        leftPathNodes[4] = left240Node;

        leftPoint.rotateAroundDeg(center, -60);

        PathNode left300Node = new PathNode(new Vector3(leftPoint.x, position.y, leftPoint.y));
        path300Nodes.add(left300Node);

        leftPathNodes[5] = left300Node;

        PathNode right0Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));
        path0Nodes.add(right0Node);

        rightPathNodes[0] = right0Node;

        rightPoint.rotateAroundDeg(center, -60);

        PathNode right60Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));
        path60Nodes.add(right60Node);

        rightPathNodes[1] = right60Node;

        rightPoint.rotateAroundDeg(center, -60);

        PathNode right120Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));
        path120Nodes.add(right120Node);

        rightPathNodes[2] = right120Node;

        rightPoint.rotateAroundDeg(center, -60);

        PathNode right180Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));
        path180Nodes.add(right180Node);

        rightPathNodes[3] = right180Node;

        rightPoint.rotateAroundDeg(center, -60);

        PathNode right240Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));
        path240Nodes.add(right240Node);

        rightPathNodes[4] = right240Node;

        rightPoint.rotateAroundDeg(center, -60);

        PathNode right300Node = new PathNode(new Vector3(rightPoint.x, position.y, rightPoint.y));
        path300Nodes.add(right300Node);

        rightPathNodes[5] = right300Node;

        degreesToPathNodes.put(0, path0Nodes);
        degreesToPathNodes.put(60, path60Nodes);
        degreesToPathNodes.put(120, path120Nodes);
        degreesToPathNodes.put(180, path180Nodes);
        degreesToPathNodes.put(360, path0Nodes);
        degreesToPathNodes.put(240, path240Nodes);
        degreesToPathNodes.put(420, path60Nodes);
        degreesToPathNodes.put(-60, path300Nodes);
        degreesToPathNodes.put(300, path300Nodes);

        pathGraph.addNode(center0Node);
        pathGraph.addNode(center60Node);
        pathGraph.addNode(center120Node);
        pathGraph.addNode(center180Node);
        pathGraph.addNode(center240Node);
        pathGraph.addNode(center300Node);
        pathGraph.addNode(right0Node);
        pathGraph.addNode(right60Node);
        pathGraph.addNode(right120Node);
        pathGraph.addNode(right180Node);
        pathGraph.addNode(right240Node);
        pathGraph.addNode(right300Node);
        pathGraph.addNode(left0Node);
        pathGraph.addNode(left60Node);
        pathGraph.addNode(left120Node);
        pathGraph.addNode(left180Node);
        pathGraph.addNode(left240Node);
        pathGraph.addNode(left300Node);

        for (int i = 0; i < 6; i ++) {
            for (int j = i + 1; j < 6; j ++) {
                pathGraph.connectBothNodes(centerPathNodes[i], centerPathNodes[j], pathConnections);
                pathGraph.connectBothNodes(rightPathNodes[i], rightPathNodes[j], pathConnections);
                pathGraph.connectBothNodes(leftPathNodes[i], leftPathNodes[j], pathConnections);
            }
        }
    }
}
