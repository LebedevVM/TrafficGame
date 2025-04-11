package proto.traffic.game.map;

public class MapNodeTrio {
    private final MapNode zeroMapNode;
    private final MapNode firstMapNode;
    private final MapNode secondMapNode;

    public MapNodeTrio(MapNode zeroMapNode, MapNode firstMapNode, MapNode secondMapNode) {
        this.zeroMapNode = zeroMapNode;
        this.firstMapNode = firstMapNode;
        this.secondMapNode = secondMapNode;
    }

    public MapNode getZeroMapNode() {
        return zeroMapNode;
    }

    public MapNode getFirstMapNode() {
        return firstMapNode;
    }

    public MapNode getSecondMapNode() {
        return secondMapNode;
    }

    @Override
    public boolean equals (Object obj) {
        MapNodeTrio mapNodeTrio = (MapNodeTrio) obj;
        return mapNodeTrio.firstMapNode == firstMapNode ||
            mapNodeTrio.firstMapNode == zeroMapNode ||
            mapNodeTrio.zeroMapNode == zeroMapNode ||
            mapNodeTrio.zeroMapNode == firstMapNode ||
            mapNodeTrio.firstMapNode == secondMapNode ||
            mapNodeTrio.zeroMapNode == secondMapNode ||
            mapNodeTrio.secondMapNode == secondMapNode;
    }
}
