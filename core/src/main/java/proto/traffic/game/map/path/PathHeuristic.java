package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class PathHeuristic implements Heuristic<PathNode> {
    @Override
    public float estimate(PathNode node, PathNode endNode) {
        return 0;
    }
}
