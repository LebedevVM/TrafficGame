package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;
import proto.traffic.game.map.MapNodeTrio;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.batch.PathNodeBatch;
import proto.traffic.game.map.path.batch.PathNodeBatchFactory;

public class RoadPiece extends MapNodePiece {
    private final Model model;
    private final ModelInstance instance;

    private final MapNodeTrio mapNodeTrio;

    private final Circle destructionCircle;

    private final Array<RoadConnection> roadConnections = new Array<>();

    private final PathNodeBatch pathNodeBatch;
    private final PathGraph pathGraph;

    private float scale = 1f;

    private final int level;

    public RoadPiece (PathGraph pathGraph, MapNode mapNode, int level, int lines) {
        super(mapNode);
        mapNode.setOccupiedByRoad(true);

        this.pathGraph = pathGraph;
        this.level = level;
        this.mapNodeTrio = mapNode.getMapNodeTrio();

        destructionCircle = new Circle(mapNode.getPosition().x, mapNode.getPosition().z, Constants.roadRadius);

        pathNodeBatch = PathNodeBatchFactory.generatePathNodeBatch(mapNode.getPosition(), pathGraph, lines);

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createCylinder(1, 1, 1, 10, new Material(ColorAttribute.createDiffuse(Color.WHITE)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(mapNode.getPosition());
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
