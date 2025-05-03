package proto.traffic.game.map.path.batch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.PathNode;

public class MonoPathNodeBatch extends PathNodeBatch {
    public MonoPathNodeBatch(Vector3 position, PathGraph pathGraph) {
        super(position, pathGraph);

        Vector2 center = new Vector2(position.x, position.z);

        Vector2 centerPoint = new Vector2(position.x, position.z - Constants.pathNodeRadius);

        PathNode[] centerPathNodes = new PathNode[6];

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

        for (int i = 0; i < 6; i ++) {
            for (int j = i + 1; j < 6; j ++) {
                pathGraph.connectBothNodes(centerPathNodes[i], centerPathNodes[j], pathConnections);
            }
        }

        PathNode pathNode = new PathNode(position);

        Array<PathNode> pathNodes = new Array<>();
        pathNodes.add(pathNode);

        degreesToPathNodes.put(270, pathNodes);
        degreesToPathNodes.put(90, pathNodes);

        degreesToPathNodes.put(330, pathNodes);
        degreesToPathNodes.put(30, pathNodes);

        degreesToPathNodes.put(150, pathNodes);
        degreesToPathNodes.put(210, pathNodes);

        pathGraph.addNode(pathNode);
    }
}
