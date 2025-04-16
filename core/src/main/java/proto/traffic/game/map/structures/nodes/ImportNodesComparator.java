package proto.traffic.game.map.structures.nodes;

import java.util.Comparator;

public class ImportNodesComparator implements Comparator<ImportNode> {
    @Override
    public int compare(ImportNode o1, ImportNode o2) {
        return - o1.getNeedsNum() + o2.getNeedsNum();
    }
}
