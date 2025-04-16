package proto.traffic.game.map.structures.nodes;

import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.buildings.ProcessingBuilding;

public class ImportNode extends ParkingNode {
    private int needsNum = 0;
    private float importFrequency = 10;
    private final Timer.Task importNumIncrementTask;

    private ProcessingBuilding processingBuilding;

    public ImportNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, ProcessingBuilding building) {
        super(buildingManager, roadGraph, carManager, mapNode, building);
        this.processingBuilding = building;

        importNumIncrementTask = new Timer.Task() {
            @Override
            public void run() {
                needsNum += 1;
            }
        };

        Timer.schedule(importNumIncrementTask, importFrequency, importFrequency);
    }

    public void decreaseNeedsNum () {
        needsNum -= 1;
    }

    public int getNeedsNum() {
        return needsNum;
    }

    public void journeyEnded () {

    }
}
