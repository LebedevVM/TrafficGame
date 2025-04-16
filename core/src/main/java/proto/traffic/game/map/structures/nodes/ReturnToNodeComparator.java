package proto.traffic.game.map.structures.nodes;

import java.util.Comparator;

public class ReturnToNodeComparator implements Comparator<ReturnToNode> {
    @Override
    public int compare(ReturnToNode o1, ReturnToNode o2) {
        return o1.getNeedsNum() + o2.getNeedsNum();
    }
}
