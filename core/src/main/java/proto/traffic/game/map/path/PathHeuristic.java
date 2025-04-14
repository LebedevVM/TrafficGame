package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Heuristic;
import proto.traffic.game.constants.Constants;

public class PathHeuristic implements Heuristic<PathNode> {
    @Override
    public float estimate (PathNode startNode, PathNode endNode) {
        return startNode.getPosition().dst(endNode.getPosition()) + (startNode.getCarCrossedScore() + endNode.getCarCrossedScore())/2f* Constants.carCrossedSignificance;
    }
}
