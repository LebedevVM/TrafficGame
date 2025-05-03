package proto.traffic.game.map.structures.buildings.extraction;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;
import proto.traffic.game.map.structures.buildings.Building;
import proto.traffic.game.map.structures.buildings.BuildingNode;
import proto.traffic.game.map.structures.nodes.ExportNode;
import proto.traffic.game.map.structures.nodes.ReturnToNode;

public class ExtractionBuilding implements Building {
    private ExportNode exportNode;
    private ReturnToNode returnToNode;
    private BuildingNode buildingNode;

    public ExtractionBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Model model, Array<MapNode> mapNodes) {
        this.exportNode = new ExportNode(buildingManager, roadGraph, buildingManager.getCarManager(), mapNodes.get(0), this, buildingName);
        this.returnToNode = new ReturnToNode(buildingManager, roadGraph, buildingManager.getCarManager(), mapNodes.get(1), this);
        buildingNode = new BuildingNode(mapNodes.get(2), new ModelInstance(model));

        buildingManager.addExportNode(buildingName, this.exportNode);
        buildingManager.addReturnToNode(buildingName, this.returnToNode);
    }

    public void carSpawned () {
        returnToNode.increaseNeedsNum();
    }

    @Override
    public void render (ModelBatch modelBatch, Environment environment) {
        buildingNode.render(modelBatch, environment);
    }
}
