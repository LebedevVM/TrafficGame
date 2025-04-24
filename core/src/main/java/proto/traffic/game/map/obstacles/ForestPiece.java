package proto.traffic.game.map.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;

public class ForestPiece extends MapNodePiece {
    private ModelInstance instance;

    public ForestPiece(MapNode mapNode) {
        super(mapNode);

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createCylinder(1, 1, 1, 10, new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(mapNode.getPosition());
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }
}
