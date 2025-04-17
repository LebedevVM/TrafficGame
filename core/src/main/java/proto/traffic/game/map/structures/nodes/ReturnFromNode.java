package proto.traffic.game.map.structures.nodes;

import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.ProcessingBuilding;

public class ReturnFromNode extends ParkingNode {
    private int carNum = 0;

    private ProcessingBuilding processingBuilding;

    public ReturnFromNode(BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, ProcessingBuilding processingBuilding) {
        super(buildingManager, roadGraph, carManager, mapNode);
        this.processingBuilding = processingBuilding;
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

        carManager.addCar(pathNode, returnToNode.getPathNode(), null);
        carNum -= 1;
        returnToNode.decreaseNeedsNum();
    }

    public void increaseCarNum () {
        carNum += 1;
    }
}
