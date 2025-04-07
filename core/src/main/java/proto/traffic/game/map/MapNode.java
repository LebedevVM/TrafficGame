package proto.traffic.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.constants.Constants;

public class MapNode {
    private Vector3 position;
    private Circle circle;
    private Circle rangeCircle;

    public Model model;
    public ModelInstance instance;

    private MapNodeTrio mapNodeTrio;

    private boolean isOccupiedByRoad = false;
    private boolean isOccupiedByObstacle = false;

    ModelBuilder modelBuilder;

    public MapNode(Vector3 position) {
        this.position = position;
        circle = new Circle(position.x, position.z, Constants.mapNodeDistance/2f);
        rangeCircle = new Circle(position.x, position.z, Constants.mapNodeDistance);

        modelBuilder = new ModelBuilder();
        model = modelBuilder.createSphere(1, 1, 1, 10, 10, new Material(ColorAttribute.createDiffuse(Color.YELLOW)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(position);
    }

    public boolean clicked (Vector2 click) {
        if (click == null) {
            return false;
        }

        return circle.contains(click);
    }

    public boolean isInRange (MapNode mapNode) {
        return this.rangeCircle.overlaps(mapNode.rangeCircle);
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }

    public Vector3 getPosition() {
        return position;
    }

    public boolean isOccupiedByRoad() {
        return isOccupiedByRoad;
    }

    public boolean isOccupiedByObstacle() {
        return isOccupiedByObstacle;
    }

    public void setOccupiedByObstacle(boolean occupiedByObstacle) {
        isOccupiedByObstacle = occupiedByObstacle;
    }

    public void setOccupiedByRoad(boolean occupiedByRoad) {
        isOccupiedByRoad = occupiedByRoad;
    }

    public MapNodeTrio getMapNodeTrio() {
        return mapNodeTrio;
    }

    public void setMapNodeTrio(MapNodeTrio mapNodeTrio) {
        this.mapNodeTrio = mapNodeTrio;
    }

    @Override
    public boolean equals(Object obj) {
        MapNode mapNode = (MapNode) obj;
        return mapNode.mapNodeTrio.equals(mapNodeTrio);
    }
}
