package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.screens.GameScreen;

public class RoadGraph {
    ObjectMap<MapNode, RoadPiece> roadPieces = new ObjectMap<>();
    Array<RoadConnection> roadConnections = new Array<>();

    private final GameScreen gameScreen;

    public RoadGraph(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

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
        if (!roadConnection.getEnd().isInRange(roadConnection.getStart())) {
            return;
        }
        if (roadConnection.getEnd() == roadConnection.getStart()) {
            return;
        }
//        if (!roadConnection.getStart().isDestructible() && !roadConnection.getEnd().isDestructible()) {
//            return;
//        }
        float level = (roadConnection.getEnd().getLevel() + roadConnection.getStart().getLevel())/2f;
        float lines = (roadConnection.getEnd().getLines() + roadConnection.getStart().getLines())/2f;
        float cost = (1 + (level)*0.25f)*(1 + (lines-1)*0.25f);
        if (gameScreen.getBudget() < cost) {
            return;
        }

        gameScreen.decreaseBudget(cost);

        roadConnection.connectPathNodes();
        roadConnections.add(roadConnection);
        roadConnection.getEnd().addRoadConnection(roadConnection);
        roadConnection.getStart().addRoadConnection(roadConnection);
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
        roadPiece.destroy(this);
    }

    public void roadPieceDestroyed (RoadPiece roadPiece) {
        roadPieces.remove(roadPiece.getMapNode());
    }

    public void destroyRoadConnection (RoadConnection roadConnection) {
        roadConnection.destroyPathConnections();
    }

    public void roadConnectionDestroyed (RoadConnection roadConnection) {
        roadConnections.removeValue(roadConnection, true);
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
