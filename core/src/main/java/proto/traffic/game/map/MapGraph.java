package proto.traffic.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.constants.Constants;

import java.util.HashMap;

public class MapGraph {
    private Array<MapNode> zeroNodes = new Array<>();
    private Array<MapNode> firstNodes = new Array<>();

    private int mapNodeDistance = Constants.mapNodeDistance;

    private ModelInstance modelInstance;

    public MapGraph () {
        Vector2 startPos = new Vector2(-25, -25);
        Vector2 addingVector = new Vector2(mapNodeDistance, 0);
        addingVector.setAngleDeg(30);
//        addingVector.setAngleDeg(0);

        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 10; j ++) {
                MapNode zeroMapNode = new MapNode(new Vector3(startPos.x, 0, startPos.y + j * mapNodeDistance));
                MapNode firstMapNode = new MapNode(new Vector3(startPos.x, Constants.bridgeHeight, startPos.y + j * mapNodeDistance));
                zeroNodes.add(zeroMapNode);
                firstNodes.add(firstMapNode);

                MapNodeTrio mapNodeTrio = new MapNodeTrio(zeroMapNode, firstMapNode);

                zeroMapNode.setMapNodeTrio(mapNodeTrio);
                firstMapNode.setMapNodeTrio(mapNodeTrio);
            }
            startPos.add(addingVector);
            addingVector.setAngleDeg(-addingVector.angleDeg());
        }

//        addingVector.setAngleDeg(30);
    }

    public void click (Vector2 vector2) {
        for (MapNode zeroNode : zeroNodes) {
            if (zeroNode.clicked(vector2)) {
                System.out.println(zeroNodes.indexOf(zeroNode, true));
            }
        }
    }

    public MapNode mouseClick (Vector2 vector2, int level) {
        if (level == 0) {
            return checkZeroNodes(vector2);
        }
        if (level == 1) {
            return checkFirstNodes(vector2);
        }

        return null;
    }

    private MapNode checkZeroNodes (Vector2 vector2) {
        for (MapNode zeroNode : zeroNodes) {
            if (zeroNode.clicked(vector2)) {
                return zeroNode;
            }
        }

        return null;
    }

    private MapNode checkFirstNodes (Vector2 vector2) {
        for (MapNode firstNode : firstNodes) {
            if (firstNode.clicked(vector2)) {
                return firstNode;
            }
        }

        return null;
    }

    public void show (ModelBatch batch, Environment environment) {
        for (MapNode zeroNode : zeroNodes) {
            zeroNode.show(batch, environment);
        }
        for (MapNode firstNode : firstNodes) {
            firstNode.show(batch, environment);
        }

//        batch.render(modelInstance, environment);
    }
}
