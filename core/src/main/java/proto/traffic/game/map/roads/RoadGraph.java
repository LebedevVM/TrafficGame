package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
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
        if (roadConnections.contains(roadConnection, false)) {
            return;
        }
        if (!roadConnection.end.isInRange(roadConnection.start)) {
            return;
        }
        if (roadConnection.end == roadConnection.start) {
            return;
        }

        roadConnections.add(roadConnection);

        System.out.println(roadConnections.size);
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
