package proto.traffic.game.map.roads;

import com.badlogic.gdx.math.Vector2;

public class RoadDestructor {
    private RoadGraph roadGraph;

    public RoadDestructor(RoadGraph roadGraph) {
        this.roadGraph = roadGraph;
    }

    public void mouseTouchDown (Vector2 vector2) {
        RoadPiece roadPiece = roadGraph.getRoadPieceByClick(RoadConstructor.getPositionOnPlane(vector2));
        RoadConnection roadConnection = roadGraph.getRoadConnectionByClick(RoadConstructor.getPositionOnPlane(vector2));

        if (roadPiece != null) {
            roadGraph.destroyRoadPiece(roadPiece);
            return;
        }
        if (roadConnection != null) {
            roadGraph.destroyRoadConnection(roadConnection);
        }
    }

    public void mouseDragged (Vector2 vector2) {
        mouseTouchDown(vector2);
    }
}
