package proto.traffic.game.map;

import com.badlogic.gdx.math.Vector3;

public class MapNodePiece {
    protected MapNode mapNode;

    public MapNodePiece(MapNode mapNode) {
        this.mapNode = mapNode;
        mapNode.setOccupiedByRoad(true);
    }

    public MapNode getMapNode() {
        return mapNode;
    }

    public Vector3 getPosition () {
        return mapNode.getPosition();
    }
}
