package proto.traffic.game.map.structures.buildings;

import com.badlogic.gdx.graphics.Color;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.nodes.ExportNode;
import proto.traffic.game.map.structures.nodes.ImportNode;
import proto.traffic.game.map.structures.nodes.ReturnFromNode;
import proto.traffic.game.map.structures.nodes.ReturnToNode;


public class ProcessingBuilding {
    private ImportNode importNode;
    private ReturnFromNode returnFromNode;
    private Color color;

    public ProcessingBuilding (BuildingManager buildingManager, RoadGraph roadGraph, Color color, MapNode importNode, MapNode returnFromNode) {
        this.color = color;

        this.importNode = new ImportNode(buildingManager, roadGraph, buildingManager.getCarManager(), importNode, this);
        this.returnFromNode = new ReturnFromNode(buildingManager, roadGraph, buildingManager.getCarManager(), returnFromNode, this);

        buildingManager.addImportNode(this.importNode);
        buildingManager.addReturnFromNode(this.returnFromNode);
    }

    public void carReached () {
        returnFromNode.increaseCarNum();
    }
}
