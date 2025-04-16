package proto.traffic.game.map.structures.nodes;

import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.Building;

public class ReturnFromNode extends ParkingNode {
    private int carNum = 0;

    public ReturnFromNode(BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, Building building) {
        super(buildingManager, roadGraph, carManager, mapNode, building);
    }

    public void spawnCar () {
        if (carNum < 1) {
            return;
        }
        if (carManager.checkCollision(sphere)) {
            return;
        }
        ReturnToNode returnToNode = buildingManager.getReturnToNode(pathNode);

        if (returnToNode == null) {
            return;
        }

        carManager.addCar(pathNode, returnToNode.getPathNode());
        carNum -= 1;
        building.carSpawned();
        returnToNode.decreaseNeedsNum();
    }

    public void increaseCarNum () {
        carNum += 1;
    }
}
