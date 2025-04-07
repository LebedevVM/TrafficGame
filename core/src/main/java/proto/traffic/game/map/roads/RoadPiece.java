package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.MapNodePiece;
import proto.traffic.game.map.MapNodeTrio;

public class RoadPiece extends MapNodePiece {
    private Model model;
    private ModelInstance instance;

    private MapNodeTrio mapNodeTrio;

    private int level;

    public RoadPiece (MapNode mapNode, int level) {
        super(mapNode);

        this.level = level;
        this.mapNodeTrio = mapNode.getMapNodeTrio();

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

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        RoadPiece roadPiece = (RoadPiece) obj;
        return roadPiece.mapNodeTrio.equals(mapNodeTrio);
    }
}
