package proto.traffic.game.map.structures.buildings.areas;

import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;

public class BuildingConstructionArea {
    protected Array<MapNode> mapNodes = new Array<>();

    public BuildingConstructionArea (MapGraph mapGraph) {}

    public Array<MapNode> getMapNodesForBuilding () {
        Array<MapNode> mapNodesForBuilding = new Array<>();

        MapNode mapNode = mapNodes.random();

        mapNodesForBuilding.add(mapNode);

        if (mapNode.isOccupied()) {
            return mapNodesForBuilding;
        }

        for (MapNode mapNode1 : mapNodes) {
            if (mapNode1.isInRange(mapNode) && !mapNode1.isOccupied() && mapNode1 != mapNode) {
                mapNodesForBuilding.add(mapNode1);
                if (mapNodesForBuilding.size == 3) {
                    break;
                }
            }
        }

        return mapNodesForBuilding;
    }
}
