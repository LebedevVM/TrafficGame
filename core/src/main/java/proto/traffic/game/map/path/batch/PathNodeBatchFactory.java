package proto.traffic.game.map.path.batch;

import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.map.path.PathGraph;

public class PathNodeBatchFactory {
    public static PathNodeBatch generatePathNodeBatch (Vector3 position, PathGraph pathGraph, int lines) {
        if (lines == 1) {
            return new MonoPathNodeBatch(position, pathGraph);
        }
        if (lines == 2) {
            return new DoublePathNodeBatch(position, pathGraph);
        }
        if (lines == 3) {
            return new TriplePathNodeBatch(position, pathGraph);
        }
        return null;
    }
}
