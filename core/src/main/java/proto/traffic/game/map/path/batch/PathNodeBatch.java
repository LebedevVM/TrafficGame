package proto.traffic.game.map.path.batch;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.map.path.PathConnection;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.PathNode;

public class PathNodeBatch {
    protected ObjectMap<Integer, Array<PathNode>> degreesToPathNodes = new ObjectMap<>();
    protected Vector3 position;
    protected PathGraph pathGraph;
    protected Array<PathConnection> pathConnections = new Array<>();

    public PathNodeBatch(Vector3 position, PathGraph pathGraph) {
        this.position = position;
        this.pathGraph = pathGraph;
    }

    public void destroy () {
        pathGraph.destroyNodeConnection(pathConnections);
        for (Array<PathNode> pathNodes : degreesToPathNodes.values().toArray()) {
            pathGraph.destroyNode(pathNodes);
        }
    }

    public Array<PathNode> getPathNodeByDegrees (int degrees) {
        return degreesToPathNodes.get(degrees);
    }
}
