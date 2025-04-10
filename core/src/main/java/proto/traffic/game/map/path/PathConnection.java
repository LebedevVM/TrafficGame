package proto.traffic.game.map.path;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class PathConnection implements Connection<PathNode> {
    private float cost = 1;
    private final PathNode toNode;
    private final PathNode fromNode;

    ModelInstance modelInstance;

    public PathConnection (PathNode fromNode, PathNode toNode) {
        this.toNode = toNode;
        this.fromNode = fromNode;

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createArrow(fromNode.getPosition(), toNode.getPosition(), new Material(ColorAttribute.createDiffuse(Color.RED)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        modelInstance = new ModelInstance(model);
//        modelInstance.transform.setToTranslation(toNode.getPosition());

//        cost = toNode.getPosition().dst(fromNode.getPosition());
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(modelInstance, environment);
    }

    public boolean contains (PathNode road) {
        return toNode == road || fromNode == road;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public PathNode getFromNode() {
        return fromNode;
    }

    @Override
    public PathNode getToNode() {
        return toNode;
    }
}
