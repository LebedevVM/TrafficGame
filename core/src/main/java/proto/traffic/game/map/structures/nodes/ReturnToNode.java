package proto.traffic.game.map.structures.nodes;

import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.Building;

public class ReturnToNode extends ParkingNode {
    private int needsNum = 0;

    public ReturnToNode(BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, Building building) {
        super(buildingManager, roadGraph, carManager, mapNode, building);
    }

    public void increaseNeedsNum () {
        needsNum += 1;
    }

    public void decreaseNeedsNum () {
        needsNum -= 1;
    }

    public int getNeedsNum() {
        return needsNum;
    }
}
