package proto.traffic.game.map.roads;

import proto.traffic.game.map.MapNode;
import proto.traffic.game.map.path.PathGraph;

public class IndestructibleRoadPiece extends RoadPiece {
    public IndestructibleRoadPiece (PathGraph pathGraph, MapNode mapNode, int level, int lines) {
        super(pathGraph, mapNode, level, lines);
    }

    @Override
    public boolean isDestructible () {
        return false;
    }
}
