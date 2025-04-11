package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import proto.traffic.game.Starter;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.path.PathGraph;

public class RoadConstructor {
    public PerspectiveCamera cam;

    private MapGraph mapGraph;
    private RoadGraph roadGraph;
    private PathGraph pathGraph;

    private static int level = 0;
    private int roadLine = 2;

    private boolean bridgeTransition = false;
    private boolean secondToZeroTransition = false;

    private RoadPiece lastRoadPiece;

    public RoadConstructor(MapGraph mapGraph, RoadGraph roadGraph, PathGraph pathGraph, PerspectiveCamera cam) {
        this.mapGraph = mapGraph;
        this.roadGraph = roadGraph;
        this.pathGraph = pathGraph;
        this.cam = cam;
    }

    public void mouseTouchDown (Vector2 position) {
        MapNode mapNode = mapGraph.mouseClick(getPositionOnPlane(position), level);

        if (mapNode == null) {
            return;
        }
        if (lastRoadPiece != null) {
            if (lastRoadPiece.getMapNodeTrio().equals(mapNode.getMapNodeTrio())) {
                return;
            }
        }
        if (mapNode.isOccupiedByObstacle()) {
            lastRoadPiece = null;
            return;
        }
        if (!mapNode.isOccupiedByRoad()) {
            RoadPiece roadPiece = new RoadPiece(pathGraph, mapNode, level, roadLine);
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
        if (bridgeTransition) {
            if (lastRoadPiece.getMapNode().equals(mapNode)) {
                return;
            }
        }

        if (!mapNode.isOccupiedByRoad()) {
            RoadPiece roadPiece = new RoadPiece(pathGraph, mapNode, level, roadLine);

            roadGraph.addRoadPiece(mapNode, roadPiece);

            roadGraph.addRoadConnection(makeRoadConnection(lastRoadPiece, roadPiece));
        }
        else {
            RoadPiece roadPiece = roadGraph.getRoadPiece(mapNode);

            if (roadPiece != null) {
                roadGraph.addRoadConnection(makeRoadConnection(lastRoadPiece, roadPiece));
            }
        }
    }

    private RoadConnection makeRoadConnection (RoadPiece start, RoadPiece end) {
        lastRoadPiece = end;
        if (start.getLevel() != end.getLevel()) {
            if (start.getLevel() == 0) {
                if (end.getLevel() == 1) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(start, end);
                }
                if (end.getLevel() == 2) {
                    roadGraph.destroyRoadPiece(end);
                    RoadPiece roadPiece = new RoadPiece(pathGraph, end.getMapNodeTrio().getFirstMapNode(), 1, roadLine);
                    roadGraph.addRoadPiece(end.getMapNodeTrio().getFirstMapNode(), roadPiece);
                    lastRoadPiece = roadPiece;
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(start, roadPiece);
                }
            }
            if (start.getLevel() == 2) {
                if (end.getLevel() == 1) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(start, end);
                }
                if (end.getLevel() == 0) {
                    roadGraph.destroyRoadPiece(end);
                    RoadPiece roadPiece = new RoadPiece(pathGraph, end.getMapNodeTrio().getFirstMapNode(), 1, roadLine);
                    roadGraph.addRoadPiece(end.getMapNodeTrio().getFirstMapNode(), roadPiece);
                    lastRoadPiece = roadPiece;
                    return RoadFactory.makeFirstToSecondMonoRoadConnection(start, roadPiece);
                }
            }
            if (start.getLevel() == 1) {
                if (end.getLevel() == 0) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(start, end);
                }
                if (end.getLevel() == 2) {
                    return RoadFactory.makeFirstToSecondMonoRoadConnection(start, end);
                }
            }
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
        if (level == 2) {
            return RoadFactory.makeSecondMonoRoadConnection(start, end);
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

    public static Vector2 getPositionOnPlane (Vector2 position) {
        Ray pickRay = Starter.cam.getPickRay(position.x, position.y);

        Plane plane = new Plane(new Vector3(0, 1 + level*Constants.bridgeHeight, 0), Vector3.Zero);
        Vector3 intersection = new Vector3();
        if (Intersector.intersectRayPlane(pickRay, plane, intersection)) {
            return new Vector2(intersection.x, intersection.z);
        } else {
            return null;
        }
    }
}
