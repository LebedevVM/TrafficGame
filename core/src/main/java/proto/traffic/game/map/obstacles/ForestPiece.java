package proto.traffic.game.map.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import proto.traffic.game.Starter;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;

public class ForestPiece extends MapNodePiece {
    private ModelInstance instance;

    public ForestPiece (MapNode mapNode) {
        super(mapNode);

        Model model = Starter.assetManager.get("ForestPiece.g3db", Model.class);

        instance = new ModelInstance(model);
        instance.transform.setToTranslation(mapNode.getPosition());
        this.instance.transform.scale(Constants.scale, Constants.scale, Constants.scale);
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }
}
