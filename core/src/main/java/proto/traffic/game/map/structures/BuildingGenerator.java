package proto.traffic.game.map.structures;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.buildings.areas.*;

public class BuildingGenerator {
    private final CowBuildingConstructionArea cowBuildingConstructionArea;
    private final CreamBuildingConstructionArea creamBuildingConstructionArea;
    private final FarmBuildingConstructionArea farmBuildingConstructionArea;
    private final LumberBuildingConstructionArea lumberBuildingConstructionArea;
    private final MillBuildingConstructionArea millBuildingConstructionArea;
    private final WoodBuildingConstructionArea woodBuildingConstructionArea;

    private final BuildingManager buildingManager;
    private final RoadGraph roadGraph;

    public BuildingGenerator(BuildingManager buildingManager, RoadGraph roadGraph, MapGraph mapGraph) {
        this.buildingManager = buildingManager;
        this.roadGraph = roadGraph;

        cowBuildingConstructionArea = new CowBuildingConstructionArea(mapGraph);
        creamBuildingConstructionArea = new CreamBuildingConstructionArea(mapGraph);
        farmBuildingConstructionArea = new FarmBuildingConstructionArea(mapGraph);
        lumberBuildingConstructionArea = new LumberBuildingConstructionArea(mapGraph);
        millBuildingConstructionArea = new MillBuildingConstructionArea(mapGraph);
        woodBuildingConstructionArea = new WoodBuildingConstructionArea(mapGraph);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                generateBuildings();
            }
        }, 0, Constants.buildingFrequency);
    }

    private void generateBuildings () {
        int randomInt = generateRandomInt(1, 3);
        if (randomInt == 1) {
            generateFarm();
        }
        if (randomInt == 2) {
            generateCow();
        }
        if (randomInt == 3) {
            generateWood();
        }
    }

    private void generateFarm () {
        Array<Array<MapNode>> array = getMapNodesForBuilding(farmBuildingConstructionArea, millBuildingConstructionArea);
        if (array == null) {
            return;
        }

        Array<MapNode> exportMapNodes = array.get(0);
        Array<MapNode> importMapNodes = array.get(1);

        Building extractionBuilding = BuildingFactory.makeFarm(buildingManager, roadGraph, exportMapNodes);
        Building processingBuilding = BuildingFactory.makeMill(buildingManager, roadGraph, importMapNodes);

        buildingManager.addBuilding(extractionBuilding);
        buildingManager.addBuilding(processingBuilding);
    }

    private void generateWood () {
        Array<Array<MapNode>> array = getMapNodesForBuilding(woodBuildingConstructionArea, lumberBuildingConstructionArea);
        if (array == null) {
            return;
        }

        Array<MapNode> exportMapNodes = array.get(0);
        Array<MapNode> importMapNodes = array.get(1);

        Building extractionBuilding = BuildingFactory.makeWood(buildingManager, roadGraph, exportMapNodes);
        Building processingBuilding = BuildingFactory.makeLumber(buildingManager, roadGraph, importMapNodes);

        buildingManager.addBuilding(extractionBuilding);
        buildingManager.addBuilding(processingBuilding);
    }

    private void generateCow () {
        Array<Array<MapNode>> array = getMapNodesForBuilding(cowBuildingConstructionArea, creamBuildingConstructionArea);
        if (array == null) {
            return;
        }

        Array<MapNode> exportMapNodes = array.get(0);
        Array<MapNode> importMapNodes = array.get(1);

        Building extractionBuilding = BuildingFactory.makeCow(buildingManager, roadGraph, exportMapNodes);
        Building processingBuilding = BuildingFactory.makeCream(buildingManager, roadGraph, importMapNodes);

        buildingManager.addBuilding(extractionBuilding);
        buildingManager.addBuilding(processingBuilding);
    }

    private Array<Array<MapNode>> getMapNodesForBuilding (BuildingConstructionArea importBC, BuildingConstructionArea exportBC) {
        Array<MapNode> exportMapNodes = farmBuildingConstructionArea.getMapNodesForBuilding();
        Array<MapNode> importMapNodes = millBuildingConstructionArea.getMapNodesForBuilding();

        for (int i = 0; i < 10; i ++) {
            if (exportMapNodes.size < 3) {
                exportMapNodes = farmBuildingConstructionArea.getMapNodesForBuilding();
            }
            else {
                break;
            }
        }

        for (int i = 0; i < 10; i ++) {
            if (importMapNodes.size < 3) {
                importMapNodes = millBuildingConstructionArea.getMapNodesForBuilding();
            }
            else {
                break;
            }
        }

        if (exportMapNodes.size < 3 || importMapNodes.size < 3) {
            return null;
        }
        Array<Array<MapNode>> array = new Array<>();
        array.add(exportMapNodes);
        array.add(importMapNodes);
        return array;
    }

    private int generateRandomInt (int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
