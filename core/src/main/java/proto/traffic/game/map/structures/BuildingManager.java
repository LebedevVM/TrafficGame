package proto.traffic.game.map.structures;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.path.PathNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.nodes.*;

public class BuildingManager {
    private final ObjectMap<String, Array<ExportNode>> exportNodes = new ObjectMap<>();
    private final ObjectMap<String, Array<ImportNode>> importNodes = new ObjectMap<>();
    private final ObjectMap<String, Array<ReturnToNode>> returnToNodes = new ObjectMap<>();
    private final ObjectMap<String, Array<ReturnFromNode>> returnFromNodes = new ObjectMap<>();

    private final Array<Building> buildings = new Array<>();

    private final ImportNodesComparator importNodesComparator = new ImportNodesComparator();
    private final ReturnToNodeComparator returnToNodeComparator = new ReturnToNodeComparator();

    private CarManager carManager;

    public BuildingManager(CarManager carManager, RoadGraph roadGraph, MapGraph mapGraph) {
        this.carManager = carManager;

        Array<ExportNode> farmExportNodes = new Array<>();
        Array<ExportNode> cowExportNodes = new Array<>();
        Array<ExportNode> woodExportNodes = new Array<>();
        exportNodes.put("farm", farmExportNodes);
        exportNodes.put("cow", cowExportNodes);
        exportNodes.put("wood", woodExportNodes);

        Array<ReturnToNode> farmReturnToNodes = new Array<>();
        Array<ReturnToNode> cowReturnToNodes = new Array<>();
        Array<ReturnToNode> woodReturnToNodes = new Array<>();
        returnToNodes.put("farm", farmReturnToNodes);
        returnToNodes.put("cow", cowReturnToNodes);
        returnToNodes.put("wood", woodReturnToNodes);

        Array<ImportNode> creamImportNodes = new Array<>();
        Array<ImportNode> millImportNodes = new Array<>();
        Array<ImportNode> lumberImportNodes = new Array<>();
        importNodes.put("cow", creamImportNodes);
        importNodes.put("farm", millImportNodes);
        importNodes.put("wood", lumberImportNodes);

        Array<ReturnFromNode> creamReturnFromNodes = new Array<>();
        Array<ReturnFromNode> millReturnFromNodes = new Array<>();
        Array<ReturnFromNode> lumberReturnFromNodes = new Array<>();
        returnFromNodes.put("cow", creamReturnFromNodes);
        returnFromNodes.put("farm", millReturnFromNodes);
        returnFromNodes.put("wood", lumberReturnFromNodes);

        BuildingGenerator buildingGenerator = new BuildingGenerator(this, roadGraph, mapGraph);
    }

    public ImportNode getImportNode (String buildingName, PathNode startNode) {
        importNodes.get(buildingName).sort(importNodesComparator);
        for (ImportNode importNode : importNodes.get(buildingName)) {
            if (carManager.isRouteAccessible(startNode, importNode.getPathNode()) && importNode.getNeedsNum() > 0) {
                return importNode;
            }
        }

        return null;
    }

    public ReturnToNode getReturnToNode (String buildingName,PathNode startNode) {
        returnToNodes.get(buildingName).sort(returnToNodeComparator);
        for (ReturnToNode returnToNode : returnToNodes.get(buildingName)) {
            if (carManager.isRouteAccessible(startNode, returnToNode.getPathNode()) && returnToNode.getNeedsNum() > 0) {
                return returnToNode;
            }
        }

        return null;
    }

    public void render () {
        for (Array<ExportNode> nodes : exportNodes.values().toArray()) {
            for (ExportNode node : nodes) {
                node.spawnCar();
            }
        }
        for (Array<ReturnFromNode> nodes : returnFromNodes.values().toArray()) {
            for (ReturnFromNode node : nodes) {
                node.spawnCar();
            }
        }
        for (Building building : buildings) {
            building.render();
        }
    }

    public void addBuilding (Building building) {
        buildings.add(building);
    }

    public void addExportNode (String buildingName, ExportNode exportNode) {
        exportNodes.get(buildingName).add(exportNode);
    }

    public void addImportNode (String buildingName, ImportNode importNode) {
        importNodes.get(buildingName).add(importNode);
    }

    public void addReturnToNode (String buildingName, ReturnToNode returnToNode) {
        returnToNodes.get(buildingName).add(returnToNode);
    }

    public void  addReturnFromNode (String buildingName, ReturnFromNode returnFromNode) {
        returnFromNodes.get(buildingName).add(returnFromNode);
    }

    public CarManager getCarManager () {
        return carManager;
    }
}
