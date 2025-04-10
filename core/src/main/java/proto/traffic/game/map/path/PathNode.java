package proto.traffic.game.map.path;

import com.badlogic.gdx.math.Vector3;

public class PathNode {
    private Vector3 position;

    public PathNode(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }
}
