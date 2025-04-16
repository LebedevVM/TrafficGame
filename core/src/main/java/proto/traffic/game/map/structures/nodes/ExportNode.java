package proto.traffic.game.map.structures.nodes;

import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.Building;

public class ExportNode extends ParkingNode {
    private int exportNum = 0;
    private float exportFrequency = 10;
    private Timer.Task exportNumIncrementTask;

    public ExportNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, Building building) {
        super(buildingManager, roadGraph, carManager, mapNode, building);
        exportNumIncrementTask = new Timer.Task() {
            @Override
            public void run() {
                exportNum += 1;
            }
        };

        Timer.schedule(exportNumIncrementTask, exportFrequency, exportFrequency);
    }

    public void spawnCar () {
        if (exportNum < 1) {
            return;
        }
        if (carManager.checkCollision(sphere)) {
            return;
        }
        ImportNode importNode = buildingManager.getImportNode(pathNode);

        if (importNode == null) {
            return;
        }

        carManager.addCar(pathNode, importNode.getPathNode());
        building.carSpawned();
        exportNum -= 1;
        importNode.decreaseNeedsNum();
    }
}
