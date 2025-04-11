package proto.traffic.game.map.path;

import com.badlogic.gdx.math.Vector3;

public class PathNode {
    private final Vector3 position;
    private int index;

    public PathNode(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
