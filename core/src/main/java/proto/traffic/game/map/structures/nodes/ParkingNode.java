package proto.traffic.game.map.structures.nodes;

import com.badlogic.gdx.math.collision.Sphere;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.path.PathNode;
import proto.traffic.game.map.roads.IndestructibleRoadPiece;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.roads.RoadPiece;
import proto.traffic.game.map.structures.BuildingManager;

public class ParkingNode {
    protected PathNode pathNode;
    protected RoadPiece roadPiece;

    protected Sphere sphere;

    protected CarManager carManager;
    protected BuildingManager buildingManager;

    public ParkingNode(BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode) {
        this.buildingManager = buildingManager;
        this.carManager = carManager;
        this.roadPiece = new IndestructibleRoadPiece(carManager.getPathGraph(), mapNode, 0, 1);
        this.pathNode = roadPiece.getPathNodeBatch().getPathNodeByDegrees(0).first();
        this.sphere = new Sphere(mapNode.getPosition(), Constants.carSightRadius);
        roadGraph.addRoadPiece(mapNode, roadPiece);
    }

    public PathNode getPathNode() {
        return pathNode;
    }
}
