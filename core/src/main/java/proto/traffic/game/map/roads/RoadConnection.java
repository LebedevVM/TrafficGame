package proto.traffic.game.map.roads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.map.MapNodePiece;

public class RoadConnection {
    protected RoadPiece start;
    protected RoadPiece end;

    private ModelInstance modelInstance;

    public RoadConnection (RoadPiece start, RoadPiece end, Model model) {
        this.start = start;
        this.end = end;

//        ModelBuilder modelBuilder = new ModelBuilder();
//        Model model = modelBuilder.createArrow(start.getPosition(), end.getPosition(), new Material(ColorAttribute.createDiffuse(Color.WHITE)),
//            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
//
//        modelInstance = new ModelInstance(model);
//        modelInstance.transform.setToTranslation(end.getPosition());

        Vector2 vector2 = new Vector2(start.getPosition().x - end.getPosition().x, start.getPosition().z - end.getPosition().z);

        Vector3 vector3 = new Vector3((start.getPosition().x + end.getPosition().x)/2,
            0, //(start.getPosition().y),
            (start.getPosition().z + end.getPosition().z)/2);

        modelInstance = new ModelInstance(model);
        modelInstance.transform.setToTranslation(vector3);
        modelInstance.transform.rotate(new Vector3(0, 1, 0), (90 - vector2.angleDeg()));
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(modelInstance, environment);
    }

    @Override
    public boolean equals (Object obj) {
        RoadConnection roadConnection = (RoadConnection) obj;
//        return (start == roadConnection.start && end == roadConnection.end) || (start == roadConnection.end && end == roadConnection.start);
        return (start.equals(roadConnection.start) && end.equals(roadConnection.end)) || (start.equals(roadConnection.end) && end.equals(roadConnection.start));
    }
}
