package proto.traffic.game.map.buildings;

import proto.traffic.game.cars.CarController;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.path.PathNode;
import proto.traffic.game.map.roads.RoadPiece;

public class ExportPoint {
    private PathNode pathNode;
    private RoadPiece roadPiece;

    private CarController carController;

    public ExportPoint (CarController carController, MapNode mapNode) {
        this.carController = carController;
        this.roadPiece = new RoadPiece(carController.getPathGraph(), mapNode, 1, 1);
        this.pathNode = roadPiece.getPathNodeBatch().getPathNodeByDegrees(30).first();
    }


}
