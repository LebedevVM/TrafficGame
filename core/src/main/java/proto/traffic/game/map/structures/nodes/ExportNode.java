package proto.traffic.game.map.structures.nodes;

import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.ExtractionBuilding;

public class ExportNode extends ParkingNode {
    private int exportNum = 0;
    private float exportFrequency = 5;
    private Timer.Task exportNumIncrementTask;

    private ExtractionBuilding extractionBuilding;

    public ExportNode (BuildingManager buildingManager, RoadGraph roadGraph, CarManager carManager, MapNode mapNode, ExtractionBuilding extractionBuilding) {
        super(buildingManager, roadGraph, carManager, mapNode);
        this.extractionBuilding = extractionBuilding;

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

        carManager.addCar(pathNode, importNode.getPathNode(), importNode);
        extractionBuilding.carSpawned();
        exportNum -= 1;
        importNode.decreaseNeedsNum();
    }
}
