package proto.traffic.game.map.path.batch;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.PathNode;

public class MonoPathNodeBatch extends PathNodeBatch {
    public MonoPathNodeBatch(Vector3 position, PathGraph pathGraph) {
        super(position, pathGraph);

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
