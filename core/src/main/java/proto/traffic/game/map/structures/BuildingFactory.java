package proto.traffic.game.map.structures;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.Starter;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.buildings.extraction.ExtractionBuilding;
import proto.traffic.game.map.structures.buildings.processing.ProcessingBuilding;

public class BuildingFactory {
    private final static ObjectMap<String, Model> namesToExtractionModels = new ObjectMap<>();
    private final static ObjectMap<String, Model> namesToProcessingModels = new ObjectMap<>();

    static {
        namesToExtractionModels.put("farm", Starter.assetManager.get("Farm.g3db", Model.class));
        namesToExtractionModels.put("wood", Starter.assetManager.get("Garden.g3db", Model.class));
        namesToExtractionModels.put("cow", Starter.assetManager.get("CowFarm.g3db", Model.class));
        namesToProcessingModels.put("farm", Starter.assetManager.get("Mill.g3db", Model.class));
        namesToProcessingModels.put("wood", Starter.assetManager.get("FruitFactory.g3db", Model.class));
        namesToProcessingModels.put("cow", Starter.assetManager.get("MilkFactory.g3db", Model.class));
    }

    public static Building makeFarm (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeExtractionBuilding(buildingManager, roadGraph, "farm", mapNodes, namesToExtractionModels.get("farm"));
    }

    public static Building makeWood (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeExtractionBuilding(buildingManager, roadGraph, "wood", mapNodes, namesToExtractionModels.get("wood"));
    }

    public static Building makeCow (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeExtractionBuilding(buildingManager, roadGraph, "cow", mapNodes, namesToExtractionModels.get("cow"));
    }

    public static Building makeMill (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeProcessingBuilding(buildingManager, roadGraph, "farm", mapNodes, namesToProcessingModels.get("farm"));
    }

    public static Building makeCream (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeProcessingBuilding(buildingManager, roadGraph, "cow", mapNodes, namesToProcessingModels.get("cow"));
    }

    public static Building makeLumber (BuildingManager buildingManager, RoadGraph roadGraph, Array<MapNode> mapNodes) {
        return makeProcessingBuilding(buildingManager, roadGraph, "wood", mapNodes, namesToProcessingModels.get("wood"));
    }

    public static ExtractionBuilding makeExtractionBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Array<MapNode> mapNodes, Model model) {
        return new ExtractionBuilding(buildingManager, roadGraph, buildingName, model, mapNodes);
    }

    public static ProcessingBuilding makeProcessingBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Array<MapNode> mapNodes, Model model) {
        return new ProcessingBuilding(buildingManager, roadGraph, buildingName, model, mapNodes);
    }
}
