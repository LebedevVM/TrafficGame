package proto.traffic.game.map.structures;

import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.buildings.extraction.ExtractionBuilding;
import proto.traffic.game.map.structures.buildings.processing.ProcessingBuilding;

public class BuildingFactory {
    public static Building makeFarm (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeExtractionBuilding(buildingManager, roadGraph, "farm", mapNodes);
    }

    public static Building makeWood (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeExtractionBuilding(buildingManager, roadGraph, "wood", mapNodes);
    }

    public static Building makeCow (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeExtractionBuilding(buildingManager, roadGraph, "cow", mapNodes);
    }

    public static Building makeMill (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeProcessingBuilding(buildingManager, roadGraph, "farm", mapNodes);
    }

    public static Building makeCream (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeProcessingBuilding(buildingManager, roadGraph, "cow", mapNodes);
    }

    public static Building makeLumber (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeProcessingBuilding(buildingManager, roadGraph, "wood", mapNodes);
    }

    public static ExtractionBuilding makeExtractionBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Array<MapNode> mapNodes) {
        return new ExtractionBuilding(buildingManager, roadGraph, buildingName, mapNodes);
    }

    public static ProcessingBuilding makeProcessingBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Array<MapNode> mapNodes) {
        return new ProcessingBuilding(buildingManager, roadGraph, buildingName, mapNodes);
    }
}
