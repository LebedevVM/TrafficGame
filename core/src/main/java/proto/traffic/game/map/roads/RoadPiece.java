package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.Starter;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;
import proto.traffic.game.map.MapNodeTrio;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.batch.PathNodeBatch;
import proto.traffic.game.map.path.batch.PathNodeBatchFactory;

public class RoadPiece extends MapNodePiece {
    private final ModelInstance instance;

    private final MapNodeTrio mapNodeTrio;

    private final Circle destructionCircle;

    private final Array<RoadConnection> roadConnections = new Array<>();

    private final PathNodeBatch pathNodeBatch;
    private final PathGraph pathGraph;

    private float scale = 1f;

    private final int level;
    private final int lines;

    public RoadPiece (PathGraph pathGraph, MapNode mapNode, int level, int lines) {
        super(mapNode);
        mapNode.setOccupiedByRoad(true);

        this.pathGraph = pathGraph;
        this.level = level;
        this.lines = lines;
        this.mapNodeTrio = mapNode.getMapNodeTrio();

        destructionCircle = new Circle(mapNode.getPosition().x, mapNode.getPosition().z, Constants.roadRadius);

        pathNodeBatch = PathNodeBatchFactory.generatePathNodeBatch(mapNode.getPosition(), pathGraph, lines);

        instance = new ModelInstance(Starter.assetManager.get("RoadTile.g3db", Model.class));
        instance.transform.setToTranslation(mapNode.getPosition());
        float scale = 1 + (lines - 1) * 0.5f;
        instance.transform.scale(scale, 1f, scale);
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }

    public void destroy (RoadGraph roadGraph) {
        RoadPiece roadPiece = this;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                scale -= 0.02f;
                if (scale < 0) {
                    scale = 0;
                }
                instance.transform.scale(scale, scale, scale);
            }
        }, 0, 0.01f, 49);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                roadGraph.roadPieceDestroyed(roadPiece);
            }
        }, 1f);
    }

    public boolean isInRange (RoadPiece roadPiece) {
        return roadPiece.getMapNode().isInRange(mapNode);
    }

    public int getLevel () {
        return level;
    }

    public int getLines() {
        return lines;
    }

    public boolean contains (Vector2 click) {
        return destructionCircle.contains(click);
    }

    public Array<RoadConnection> getRoadConnections () {
        return roadConnections;
    }

    public void addRoadConnection (RoadConnection roadConnection) {
        roadConnections.add(roadConnection);
    }

    public PathNodeBatch getPathNodeBatch() {
        return pathNodeBatch;
    }

    public PathGraph getPathGraph() {
        return pathGraph;
    }

    public MapNodeTrio getMapNodeTrio() {
        return mapNodeTrio;
    }

    public boolean isDestructible () {
        return true;
    }

    @Override
    public boolean equals (Object obj) {
        RoadPiece roadPiece = (RoadPiece) obj;
        return roadPiece.mapNodeTrio.equals(mapNodeTrio);
    }
}
