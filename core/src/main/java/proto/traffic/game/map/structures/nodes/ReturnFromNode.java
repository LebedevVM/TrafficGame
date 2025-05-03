package proto.traffic.game.map.structures.nodes;

import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.processing.ProcessingBuilding;

public class ReturnFromNode extends ParkingNode {
    private int carNum = 0;

    private ProcessingBuilding processingBuilding;

    private final String buildingName;

    public ReturnFromNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, ProcessingBuilding processingBuilding, String buildingName) {
        super(buildingManager, roadGraph, carManager, mapNode);
        this.processingBuilding = processingBuilding;
        this.buildingName = buildingName;
    }

    public void spawnCar () {
        if (carNum < 1) {
            return;
        }
        if (carManager.checkCollision(sphere)) {
            return;
        }
        ReturnToNode returnToNode = buildingManager.getReturnToNode(buildingName, pathNode);

        if (returnToNode == null) {
            return;
        }

        carManager.addCar(pathNode, returnToNode.getPathNode(), null, buildingName);
        carNum -= 1;
        returnToNode.decreaseNeedsNum();
    }

    public void increaseCarNum () {
        carNum += 1;
    }
}
