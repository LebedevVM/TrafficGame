package proto.traffic.game.map.structures.buildings.areas;

import com.badlogic.gdx.math.Rectangle;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;

public class LumberBuildingConstructionArea extends BuildingConstructionArea {
    public LumberBuildingConstructionArea (MapGraph mapGraph) {
        super(mapGraph);

        float x = -mapGraph.getWidth()/2;
        float y = -mapGraph.getHeight()/2;
        float len = mapGraph.getHeight()/4;
        Rectangle firstRectangle = new Rectangle(x + len*2, y, len*2, len*4);

        for (MapNode mapNode : mapGraph.getMapNodesInRectangle(firstRectangle)) {
            mapNodes.add(mapNode);
        }
    }
}
