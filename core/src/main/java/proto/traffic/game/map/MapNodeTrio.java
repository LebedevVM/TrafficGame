package proto.traffic.game.map;

public class MapNodeTrio {
    private MapNode zeroMapNode;
    private MapNode firstMapNode;

    public MapNodeTrio(MapNode zeroMapNode, MapNode firstMapNode) {
        this.zeroMapNode = zeroMapNode;
        this.firstMapNode = firstMapNode;
    }

    @Override
    public boolean equals(Object obj) {
        MapNodeTrio mapNodeTrio = (MapNodeTrio) obj;
        return mapNodeTrio.firstMapNode == firstMapNode ||
            mapNodeTrio.firstMapNode == zeroMapNode ||
            mapNodeTrio.zeroMapNode == zeroMapNode ||
            mapNodeTrio.zeroMapNode == firstMapNode;
    }
}
