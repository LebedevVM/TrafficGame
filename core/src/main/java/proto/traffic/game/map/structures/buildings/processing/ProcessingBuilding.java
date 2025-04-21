package proto.traffic.game.map.structures.buildings.processing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.nodes.ExportNode;
import proto.traffic.game.map.structures.nodes.ImportNode;
import proto.traffic.game.map.structures.nodes.ReturnFromNode;
import proto.traffic.game.map.structures.nodes.ReturnToNode;

public class ProcessingBuilding implements Building {
    private ImportNode importNode;
    private ReturnFromNode returnFromNode;

    public ProcessingBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Array<MapNode> mapNodes) {
        this.importNode = new ImportNode(buildingManager, roadGraph, buildingManager.getCarManager(), mapNodes.get(0), this);
        this.returnFromNode = new ReturnFromNode(buildingManager, roadGraph, buildingManager.getCarManager(), mapNodes.get(1), this, buildingName);

        buildingManager.addImportNode(buildingName, this.importNode);
        buildingManager.addReturnFromNode(buildingName, this.returnFromNode);
    }

    public void carReached () {
        returnFromNode.increaseCarNum();
    }

    @Override
    public void render () {

    }
}
