package proto.traffic.game.map;

import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.constants.Constants;

public class MapGraph {
    private final Array<MapNode> zeroNodes = new Array<>();
    private final Array<MapNode> firstNodes = new Array<>();
    private final Array<MapNode> secondNodes = new Array<>();

    private final int mapNodeDistance = Constants.mapNodeDistance;

    private float width = 100;
    private float height = width/6f*4f;

    public MapGraph () {
        Vector2 startPos = new Vector2(-width/2, -height/2);
        Vector2 addingVector = new Vector2(mapNodeDistance, 0);
        addingVector.setAngleDeg(30);
//        addingVector.setAngleDeg(0);

        for (int i = 0; i < width/(mapNodeDistance*Math.sqrt(3)/2); i ++) {
            for (int j = 0; j < height/mapNodeDistance; j ++) {
                MapNode zeroMapNode = new MapNode(new Vector3(startPos.x, 0, startPos.y + j * mapNodeDistance));
                MapNode firstMapNode = new MapNode(new Vector3(startPos.x, Constants.bridgeHeight, startPos.y + j * mapNodeDistance));
                MapNode secondMapNode = new MapNode(new Vector3(startPos.x, Constants.bridgeHeight*2, startPos.y + j * mapNodeDistance));
                zeroNodes.add(zeroMapNode);
                firstNodes.add(firstMapNode);
                secondNodes.add(secondMapNode);

                MapNodeTrio mapNodeTrio = new MapNodeTrio(zeroMapNode, firstMapNode, secondMapNode);

                zeroMapNode.setMapNodeTrio(mapNodeTrio);
                firstMapNode.setMapNodeTrio(mapNodeTrio);
                secondMapNode.setMapNodeTrio(mapNodeTrio);
            }
            startPos.add(addingVector);
            addingVector.setAngleDeg(-addingVector.angleDeg());
        }
    }

    public int getMapNodeIndex (MapNode mapNode) {
        return zeroNodes.indexOf(mapNode, true);
    }

    public MapNode getMapNodeByIndex (int index) {
        return zeroNodes.get(index);
    }

    public Array<MapNode> getMapNodesInRectangle (Rectangle rectangle) {
        Array<MapNode> mapNodesInRectangle = new Array<>();

        for (MapNode zeroNode : zeroNodes) {
            Vector3 pos = zeroNode.getPosition();
            if (rectangle.contains(new Vector2(pos.x, pos.z))) {
                mapNodesInRectangle.add(zeroNode);
            }
        }

        return mapNodesInRectangle;
    }

    public MapNode getRandomMapNode () {
        return zeroNodes.random();
    }

    public Array<MapNode> getMapNodesForBuilding () {
        Array<MapNode> mapNodesForBuilding = new Array<>();

        MapNode mapNode = getRandomMapNode();

        for (int i = 0; i < 10; i ++) {
            if (!mapNode.isOccupied()) {
                break;
            }
            mapNode = getRandomMapNode();
        }

        if (mapNode.isOccupied()) {
            return mapNodesForBuilding;
        }

        mapNodesForBuilding.add(mapNode);

        for (MapNode zeroNode : zeroNodes) {
            if (zeroNode.isInRange(mapNode) && !zeroNode.isOccupied()) {
                mapNodesForBuilding.add(zeroNode);
                if (mapNodesForBuilding.size == 3) {
                    break;
                }
            }
        }

        return mapNodesForBuilding;
    }

    public MapNode mouseClick (Vector2 vector2, int level) {
        if (level == 0) {
            return checkZeroNodes(vector2);
        }
        if (level == 1) {
            return checkFirstNodes(vector2);
        }
        if (level == 2) {
            return checkSecondNodes(vector2);
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

    private MapNode checkSecondNodes (Vector2 vector2) {
        for (MapNode secondNode : secondNodes) {
            if (secondNode.clicked(vector2)) {
                return secondNode;
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
        for (MapNode secondNode : secondNodes) {
            secondNode.show(batch, environment);
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
