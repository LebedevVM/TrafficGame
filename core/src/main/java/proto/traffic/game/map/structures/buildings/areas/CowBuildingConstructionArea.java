package proto.traffic.game.map.structures.buildings.areas;

import com.badlogic.gdx.math.Rectangle;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;

public class CowBuildingConstructionArea extends BuildingConstructionArea {
    public CowBuildingConstructionArea (MapGraph mapGraph) {
        super(mapGraph);

        float x = -mapGraph.getWidth()/2;
        float y = -mapGraph.getHeight()/2;
        float len = mapGraph.getHeight()/4;
        Rectangle firstRectangle = new Rectangle(x, y, len*2, len*2);
        Rectangle secondRectangle = new Rectangle(x + len*4, y + len*2, len*2, len*2);

        for (MapNode mapNode : mapGraph.getMapNodesInRectangle(firstRectangle)) {
            mapNodes.add(mapNode);
        }
        for (MapNode mapNode : mapGraph.getMapNodesInRectangle(secondRectangle)) {
            mapNodes.add(mapNode);
        }
    }
}
