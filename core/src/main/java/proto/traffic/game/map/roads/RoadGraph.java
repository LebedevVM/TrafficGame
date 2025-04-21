package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.map.MapNode;

public class RoadGraph {
    ObjectMap<MapNode, RoadPiece> roadPieces = new ObjectMap<>();
    Array<RoadConnection> roadConnections = new Array<>();

    public void addRoadPiece (MapNode mapNode, RoadPiece roadPiece) {
        roadPieces.put(mapNode, roadPiece);
    }

    public RoadPiece getRoadPiece (MapNode mapNode) {
        return roadPieces.get(mapNode);
    }

    public void addRoadConnection (RoadConnection roadConnection) {
        if (roadConnection == null) {
            return;
        }
        if (roadConnections.contains(roadConnection, false)) {
            return;
        }
        if (!roadConnection.end.isInRange(roadConnection.start)) {
            return;
        }
        if (roadConnection.end == roadConnection.start) {
            return;
        }
        if (!roadConnection.start.isDestructible() && !roadConnection.end.isDestructible()) {
            return;
        }

        roadConnection.connectPathNodes();
        roadConnections.add(roadConnection);
        roadConnection.end.addRoadConnection(roadConnection);
        roadConnection.start.addRoadConnection(roadConnection);
    }

    public RoadConnection getRoadConnectionByClick (Vector2 vector2) {
        for (RoadConnection roadConnection : roadConnections) {
            if (roadConnection.contains(vector2)) {
                return roadConnection;
            }
        }
        return null;
    }

    public RoadPiece getRoadPieceByClick (Vector2 vector2) {
        for (RoadPiece roadPiece : roadPieces.values().toArray()) {
            if (roadPiece.contains(vector2)) {
                return roadPiece;
            }
        }
        return null;
    }

    public void destroyRoadPiece (RoadPiece roadPiece) {
        roadPiece.getMapNode().setOccupiedByRoad(false);
        for (RoadConnection roadConnection : roadPiece.getRoadConnections()) {
            destroyRoadConnection(roadConnection);
        }
        roadPiece.getPathNodeBatch().destroy();
        roadPieces.remove(roadPiece.getMapNode());
    }

    public void destroyRoadConnection (RoadConnection roadConnection) {
        roadConnections.removeValue(roadConnection, true);
        roadConnection.destroyPathConnections();
    }

    public void show (ModelBatch batch, Environment environment) {
        for (RoadPiece roadPiece : roadPieces.values()) {
            roadPiece.show(batch, environment);
        }
        for (RoadConnection roadConnection : roadConnections) {
            roadConnection.show(batch, environment);
        }
    }
}
