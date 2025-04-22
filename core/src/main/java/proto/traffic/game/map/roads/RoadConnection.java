package proto.traffic.game.map.roads;


import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.path.PathConnection;
import proto.traffic.game.map.path.PathNode;

public class RoadConnection {
    private final RoadPiece start;
    private final RoadPiece end;

    private final RoadGraph roadGraph;

    private final int degrees;

    protected Circle destructionCircle;

    private final ModelInstance modelInstance;

    private final Array<PathConnection> pathConnections = new Array<>();

    private float scale = 1f;

    public RoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end, Model model) {
        this.roadGraph = roadGraph;
        this.start = start;
        this.end = end;

        Vector2 vector2 = new Vector2(start.getPosition().x - end.getPosition().x, start.getPosition().z - end.getPosition().z);

        Vector3 vector3 = new Vector3((start.getPosition().x + end.getPosition().x)/2,
            0, //(start.getPosition().y),
            (start.getPosition().z + end.getPosition().z)/2);

        destructionCircle = new Circle(vector3.x, vector3.z, Constants.mapNodeDistance/4f);

        degrees = Math.round(vector2.angleDeg());

        modelInstance = new ModelInstance(model);
        modelInstance.transform.setToTranslation(vector3);
        modelInstance.transform.rotate(new Vector3(0, 1, 0), (270 - degrees));
    }

    public void connectPathNodes () {
        Array<PathNode> startPathNodes = start.getPathNodeBatch().getPathNodeByDegrees(degrees);
        Array<PathNode> endPathNodes = end.getPathNodeBatch().getPathNodeByDegrees(degrees);
        if (startPathNodes == null || endPathNodes == null) {
            return;
        }
        for (PathConnection pathConnection : start.getPathGraph().connectNodes(startPathNodes, endPathNodes)) {
            pathConnections.add(pathConnection);
        }
    }

    public void destroyPathConnections () {
        start.getPathGraph().destroyNodeConnection(pathConnections);
        disappear();
    }

    public void disappear () {
        RoadConnection roadConnection = this;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                scale -= 0.02f;
                if (scale < 0) {
                    scale = 0;
                }
                modelInstance.transform.scale(scale, scale, scale);
            }
        }, 0, 0.01f, 49);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                roadGraph.roadConnectionDestroyed(roadConnection);
            }
        }, 1f);
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(modelInstance, environment);
    }

    public boolean contains (Vector2 click) {
        return destructionCircle.contains(click);
    }

    @Override
    public boolean equals (Object obj) {
        RoadConnection roadConnection = (RoadConnection) obj;
        return (start.equals(roadConnection.start) && end.equals(roadConnection.end)) || (start.equals(roadConnection.end) && end.equals(roadConnection.start));
    }

    public RoadPiece getStart() {
        return start;
    }

    public RoadPiece getEnd() {
        return end;
    }
}
