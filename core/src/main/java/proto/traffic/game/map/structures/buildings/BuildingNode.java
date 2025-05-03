package proto.traffic.game.map.structures.buildings;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;

public class BuildingNode extends MapNodePiece {
    private ModelInstance instance;

    public BuildingNode (MapNode mapNode, ModelInstance instance) {
        super(mapNode);
        this.instance = instance;
        this.instance.transform.setToTranslation(mapNode.getPosition());
    }

    public void render (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }
}
