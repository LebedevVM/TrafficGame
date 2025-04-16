package proto.traffic.game.map.structures.nodes;

import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;

public class ImportNode extends ParkingNode {
    public ImportNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode) {
        super(buildingManager, roadGraph, carManager, mapNode);
    }
}
