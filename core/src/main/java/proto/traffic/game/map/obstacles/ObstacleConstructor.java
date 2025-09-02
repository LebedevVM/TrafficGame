package proto.traffic.game.map.obstacles;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.google.gson.Gson;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.obstacles.data.ObstacleData;
import proto.traffic.game.screens.EditorScreen;

import java.io.FileWriter;
import java.io.IOException;

public class ObstacleConstructor {
    public PerspectiveCamera cam;

    private final MapGraph mapGraph;
    private final ObstacleGraph obstacleGraph;

    private boolean forest = true;
    private MapNode lastRiverPiece;

    private ObstacleData obstacleData = new ObstacleData();

    public ObstacleConstructor (PerspectiveCamera cam, MapGraph mapGraph, ObstacleGraph obstacleGraph) {
        this.cam = cam;
        this.mapGraph = mapGraph;
        this.obstacleGraph = obstacleGraph;
    }

    public void saveData () {
        String name = "a";

        for (ForestPiece forestPiece : obstacleGraph.getForestPieces().values().toArray()) {
            obstacleData.addForest(forestPiece);
        }
        for (RiverPiece riverPiece : obstacleGraph.getRiverPieces().values()) {
            obstacleData.addRiverPiece(riverPiece);
        }
        for (RiverConnection riverConnection : obstacleGraph.getRiverConnections()) {
            obstacleData.addRiverConnection(riverConnection);
        }

        obstacleData.setName(name);

        try {
            FileWriter fileWriter = new FileWriter("C:\\1) TrafficGame\\a.json");

            fileWriter.write(new Gson().toJson(obstacleData, ObstacleData.class));
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ignored) {}
    }

    public void changeForest () {
        forest = !forest;
    }

    public void mouseTouchDown (Vector2 position) {
        MapNode mapNode = mapGraph.mouseClick(getPositionOnPlane(position), 0);

        if (mapNode == null) {
            return;
        }

        if (forest) {
            obstacleGraph.addForestPiece(mapNode);
            return;
        }

        if (mapNode.isOccupiedByObstacle()) {
            lastRiverPiece = null;
            return;
        }
        if (!mapNode.isOccupiedByRoad()) {
            obstacleGraph.addRiverPiece(mapNode);
        }

        lastRiverPiece = mapNode;
    }

    public void mouseDragged (Vector2 position) {
        MapNode mapNode = mapGraph.mouseClick(getPositionOnPlane(position), 0);

        if (mapNode == null) {
            return;
        }

        if (forest) {
            obstacleGraph.addForestPiece(mapNode);
            return;
        }

        if (lastRiverPiece == null) {
            mouseTouchDown(position);
            return;
        }
        if (mapNode == lastRiverPiece) {
            return;
        }

        obstacleGraph.addRiverPiece(mapNode);
        obstacleGraph.connectRiverPieces(lastRiverPiece, mapNode);
        lastRiverPiece = mapNode;
    }

    public static Vector2 getPositionOnPlane (Vector2 position) {
        Ray pickRay = EditorScreen.cam.getPickRay(position.x, position.y);

        Plane plane = new Plane(new Vector3(0, 1, 0), Vector3.Zero);
        Vector3 intersection = new Vector3();
        if (Intersector.intersectRayPlane(pickRay, plane, intersection)) {
            return new Vector2(intersection.x, intersection.z);
        } else {
            return null;
        }
    }
}
