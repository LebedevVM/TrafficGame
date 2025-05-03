package proto.traffic.game.map.structures.buildings.processing;

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
import proto.traffic.game.map.structures.nodes.ImportNode;
import proto.traffic.game.map.structures.nodes.ReturnFromNode;
import proto.traffic.game.map.structures.nodes.ReturnToNode;

public class ProcessingBuilding implements Building {
    private ImportNode importNode;
    private ReturnFromNode returnFromNode;
    private BuildingNode buildingNode;

    public ProcessingBuilding (BuildingManager buildingManager, RoadGraph roadGraph, String buildingName, Model model, Array<MapNode> mapNodes) {
        this.importNode = new ImportNode(buildingManager, roadGraph, buildingManager.getCarManager(), mapNodes.get(0), this);
        this.returnFromNode = new ReturnFromNode(buildingManager, roadGraph, buildingManager.getCarManager(), mapNodes.get(1), this, buildingName);
        buildingNode = new BuildingNode(mapNodes.get(2), new ModelInstance(model));

        buildingManager.addImportNode(buildingName, this.importNode);
        buildingManager.addReturnFromNode(buildingName, this.returnFromNode);
    }

    public void carReached () {
        returnFromNode.increaseCarNum();
    }

    @Override
    public void render (ModelBatch modelBatch, Environment environment) {
        buildingNode.render(modelBatch, environment);
    }
}
