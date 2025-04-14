package proto.traffic.game.map.path;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

public class PathNode {
    private final Vector3 position;
    private int index;
    private float carCrossedScore = 0;

    public PathNode (Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition () {
        return position;
    }

    public void setIndex (int index) {
        this.index = index;
    }

    public void carCrossed () {
        carCrossedScore += 1;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                carCrossedScore -= 0.2;
            }
        }, 1, 1, 5);
    }

    public float getCarCrossedScore () {
        return carCrossedScore;
    }

    public int getIndex () {
        return index;
    }
}
