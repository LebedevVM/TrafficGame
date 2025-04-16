package proto.traffic.game.map.structures.buildings;

import com.badlogic.gdx.graphics.Color;
import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.structures.nodes.ExportNode;
import proto.traffic.game.map.structures.nodes.ReturnToNode;

public class ExtractionBuilding implements Building {
    private ExportNode exportNode;
    private ReturnToNode returnToNode;
    private Color color;

    public ExtractionBuilding(Color color, MapNode exportNode, MapNode returnToNode) {
        this.color = color;
    }

    @Override
    public void carSpawned () {
        returnToNode.increaseNeedsNum();
    }
}
