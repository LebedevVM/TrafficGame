package proto.traffic.game.map.structures.nodes;

import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.processing.ProcessingBuilding;

public class ImportNode extends ParkingNode {
    private int needsNum = 0;
    private float importFrequency = Constants.importFrequency;
    private final Timer.Task importNumIncrementTask;

    private ProcessingBuilding processingBuilding;

    public ImportNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, ProcessingBuilding processingBuilding) {
        super(buildingManager, roadGraph, carManager, mapNode);
        this.processingBuilding = processingBuilding;

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

    public int getNeedsNum () {
        return needsNum;
    }

    public void carReached () {
        processingBuilding.carReached();
    }
}
