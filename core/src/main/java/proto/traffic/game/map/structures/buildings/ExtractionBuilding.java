package proto.traffic.game.map.structures.buildings;

import com.badlogic.gdx.graphics.Color;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.nodes.ExportNode;
import proto.traffic.game.map.structures.nodes.ReturnToNode;

public class ExtractionBuilding {
    private ExportNode exportNode;
    private ReturnToNode returnToNode;
    private Color color;

    public ExtractionBuilding (BuildingManager buildingManager, RoadGraph roadGraph, Color color, MapNode exportNode, MapNode returnToNode) {
        this.color = color;

        this.exportNode = new ExportNode(buildingManager, roadGraph, buildingManager.getCarManager(), exportNode, this);
        this.returnToNode = new ReturnToNode(buildingManager, roadGraph, buildingManager.getCarManager(), returnToNode, this);

        buildingManager.addExportNode(this.exportNode);
        buildingManager.addReturnToNode(this.returnToNode);
    }

    public void carSpawned () {
        returnToNode.increaseNeedsNum();
    }
}
