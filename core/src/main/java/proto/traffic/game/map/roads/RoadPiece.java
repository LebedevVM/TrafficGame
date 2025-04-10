package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;
import proto.traffic.game.map.MapNodeTrio;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.batch.PathNodeBatch;
import proto.traffic.game.map.path.batch.PathNodeBatchFactory;

public class RoadPiece extends MapNodePiece {
    private Model model;
    private ModelInstance instance;

    private MapNodeTrio mapNodeTrio;

    private Circle destructionCircle;

    private Array<RoadConnection> roadConnections = new Array<>();

    private PathNodeBatch pathNodeBatch;
    private PathGraph pathGraph;

    private int level;

    public RoadPiece (PathGraph pathGraph, MapNode mapNode, int level, int lines) {
        super(mapNode);

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

    @Override
    public boolean equals (Object obj) {
        RoadPiece roadPiece = (RoadPiece) obj;
        return roadPiece.mapNodeTrio.equals(mapNodeTrio);
    }
}
