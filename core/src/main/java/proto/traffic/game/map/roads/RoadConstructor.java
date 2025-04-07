package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;

public class RoadConstructor {
    public PerspectiveCamera cam;

    private MapGraph mapGraph;
    private RoadGraph roadGraph;

    private int level = 0;
    private int roadLine = 1;

    private boolean bridgeTransition = false;

    private RoadPiece lastRoadPiece;
    private MapNode lastMapNode;

    public RoadConstructor(MapGraph mapGraph, RoadGraph roadGraph, PerspectiveCamera cam) {
        this.mapGraph = mapGraph;
        this.roadGraph = roadGraph;
        this.cam = cam;
    }

    public void mouseTouchDown (Vector2 position) {
        MapNode mapNode = mapGraph.mouseClick(getPositionOnPlane(position), level);

        if (mapNode == null) {
            return;
        }
        if (mapNode.isOccupiedByObstacle()) {
            lastRoadPiece = null;
            return;
        }
        if (!mapNode.isOccupiedByRoad()) {
            RoadPiece roadPiece = new RoadPiece(mapNode, level);
            roadGraph.addRoadPiece(mapNode, roadPiece);

            lastRoadPiece = roadPiece;

        }
        else {
            lastRoadPiece = roadGraph.getRoadPiece(mapNode);
        }
    }

    public void mouseDragged (Vector2 position) {
        MapNode mapNode = mapGraph.mouseClick(getPositionOnPlane(position), level);

        if (mapNode == null) {
            return;
        }
        if (mapNode.isOccupiedByObstacle()) {
            lastRoadPiece = null;
            return;
        }
        if (lastRoadPiece == null) {
            mouseTouchDown(position);
            return;
        }
        if (mapNode == lastRoadPiece.getMapNode()) {
            return;
        }

        if (!mapNode.isOccupiedByRoad()) {
            RoadPiece roadPiece = new RoadPiece(mapNode, level);
            roadGraph.addRoadPiece(mapNode, roadPiece);

            roadGraph.addRoadConnection(makeRoadConnection(lastRoadPiece, roadPiece));

            lastRoadPiece = roadPiece;

        }
        else {
            RoadPiece roadPiece = roadGraph.getRoadPiece(mapNode);

            if (roadPiece != null) {
                roadGraph.addRoadConnection(makeRoadConnection(lastRoadPiece, roadPiece));
                lastRoadPiece = roadPiece;
            }
        }
        lastMapNode = null;
    }

    private RoadConnection makeRoadConnection (RoadPiece start, RoadPiece end) {
        if (start.getLevel() != end.getLevel()) {
            return RoadFactory.makeZeroToFirstMonoRoadConnection(start, end);
        }
        if (level == 0) {
//            if (bridgeTransition) {
//                bridgeTransition = false;
//
//            }
            return RoadFactory.makeZeroMonoRoadConnection(start, end);
        }
        if (level == 1) {
//            if (bridgeTransition) {
//                bridgeTransition = false;
//                return RoadFactory.makeZeroToFirstMonoRoadConnection(start, end);
//            }
            return RoadFactory.makeFirstMonoRoadConnection(start, end);
        }
        return null;
    }

    public void increaseLevel () {
        level += 1;

        if (level > 2) {
            level = 2;
        }

        bridgeTransition = true;
    }

    public void decreaseLevel () {
        level -= 1;

        if (level < 0) {
            level = 0;
        }

        bridgeTransition = true;
    }

    private Vector2 getPositionOnPlane (Vector2 position) {
        Ray pickRay = cam.getPickRay(position.x, position.y);

        Plane plane = new Plane(new Vector3(0, 1, 0), Vector3.Zero);
        Vector3 intersection = new Vector3();
        if (Intersector.intersectRayPlane(pickRay, plane, intersection)) {
            return new Vector2(intersection.x, intersection.z);
        } else {
            return null;
        }
    }
}
