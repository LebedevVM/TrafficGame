package proto.traffic.game.map.roads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapNodePiece;
import proto.traffic.game.map.path.PathConnection;
import proto.traffic.game.map.path.PathNode;

public class RoadConnection {
    protected RoadPiece start;
    protected RoadPiece end;

    private int degrees;

    protected Circle destructionCircle;

    private ModelInstance modelInstance;

    private Array<PathConnection> pathConnections = new Array<>();

    public RoadConnection (RoadPiece start, RoadPiece end, Model model) {
        this.start = start;
        this.end = end;

        Vector2 vector2 = new Vector2(start.getPosition().x - end.getPosition().x, start.getPosition().z - end.getPosition().z);

        Vector3 vector3 = new Vector3((start.getPosition().x + end.getPosition().x)/2,
            0, //(start.getPosition().y),
            (start.getPosition().z + end.getPosition().z)/2);

        destructionCircle = new Circle(vector3.x, vector3.z, Constants.mapNodeDistance/4f);

        degrees = Math.round(vector2.angleDeg());

        modelInstance = new ModelInstance(model);
        modelInstance.transform.setToTranslation(vector3);
        modelInstance.transform.rotate(new Vector3(0, 1, 0), (270 - degrees));
    }

    public void connectPathNodes () {
        Array<PathNode> startPathNodes = start.getPathNodeBatch().getPathNodeByDegrees(degrees);
        Array<PathNode> endPathNodes = end.getPathNodeBatch().getPathNodeByDegrees(degrees);
        if (startPathNodes == null || endPathNodes == null) {
            return;
        }
        for (PathConnection pathConnection : start.getPathGraph().connectNodes(startPathNodes, endPathNodes)) {
            pathConnections.add(pathConnection);
        }
    }

    public void destroyPathConnections () {
        start.getPathGraph().destroyNodeConnection(pathConnections);
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(modelInstance, environment);
    }

    public boolean contains (Vector2 click) {
        return destructionCircle.contains(click);
    }

    @Override
    public boolean equals (Object obj) {
        RoadConnection roadConnection = (RoadConnection) obj;
        return (start.equals(roadConnection.start) && end.equals(roadConnection.end)) || (start.equals(roadConnection.end) && end.equals(roadConnection.start));
    }
}
