package proto.traffic.game.map.structures.nodes;

import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;

public class ExportNode extends ParkingNode {
    public ExportNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode) {
        super(buildingManager, roadGraph, carManager, mapNode);
    }

    public void spawnCar () {
        if (carManager.checkCollision(sphere)) {
            return;
        }
        ImportNode importNode = buildingManager.getImportNode(pathNode);

        if (importNode == null) {
            return;
        }

        carManager.addCar(pathNode, importNode.getPathNode());
    }
}
