package proto.traffic.game.map.roads;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.Starter;

public class RoadFactory {
    private static final ObjectMap<String, String> roadPropertiesToModels = new ObjectMap<>();

    static {
        roadPropertiesToModels.put("1100", "MonoZeroRoadConnection.g3db");
        roadPropertiesToModels.put("1101", "MonoZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1110", "MonoZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1111", "MonoFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1112", "MonoFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1121", "MonoFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1122", "MonoSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1200", "MonoToDoubleZeroRoadConnection.g3db");
        roadPropertiesToModels.put("2100", "MonoToDoubleZeroRoadConnection.g3db");
        roadPropertiesToModels.put("1201", "MonoToDoubleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1210", "MonoToDoubleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1211", "MonoToDoubleFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2111", "MonoToDoubleFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1212", "MonoToDoubleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1221", "MonoToDoubleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1222", "MonoToDoubleSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2122", "MonoToDoubleSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1301", "MonoToTripleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1310", "MonoToTripleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1312", "MonoToTripleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1321", "MonoToTripleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2101", "DoubleToMonoZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2110", "DoubleToMonoZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2112", "DoubleToMonoFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2121", "DoubleToMonoFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2200", "DoubleZeroRoadConnection.g3db");
        roadPropertiesToModels.put("2201", "DoubleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2210", "DoubleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2211", "DoubleFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2212", "DoubleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2221", "DoubleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2222", "DoubleSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2301", "DoubleToTripleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2310", "DoubleToTripleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2312", "DoubleToTripleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2321", "DoubleToTripleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3100", "TripleToMonoZeroRoadConnection.g3db");
        roadPropertiesToModels.put("1300", "TripleToMonoZeroRoadConnection.g3db");
        roadPropertiesToModels.put("3101", "TripleToMonoZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3110", "TripleToMonoZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3111", "TripleToMonoFirstRoadConnection.g3db");
        roadPropertiesToModels.put("1311", "TripleToMonoFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3112", "TripleToMonoFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3121", "TripleToMonoFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3122", "TripleToMonoSecondRoadConnection.g3db");
        roadPropertiesToModels.put("1322", "TripleToMonoSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3200", "TripleToDoubleZeroRoadConnection.g3db");
        roadPropertiesToModels.put("2300", "TripleToDoubleZeroRoadConnection.g3db");
        roadPropertiesToModels.put("3201", "TripleToDoubleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3210", "TripleToDoubleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3211", "TripleToDoubleFirstRoadConnection.g3db");
        roadPropertiesToModels.put("2311", "TripleToDoubleFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3212", "TripleToDoubleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3212", "TripleToDoubleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3222", "TripleToDoubleSecondRoadConnection.g3db");
        roadPropertiesToModels.put("2322", "TripleToDoubleSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3300", "TripleZeroRoadConnection.g3db");
        roadPropertiesToModels.put("3301", "TripleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3310", "TripleZeroToFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3311", "TripleFirstRoadConnection.g3db");
        roadPropertiesToModels.put("3312", "TripleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3321", "TripleFirstToSecondRoadConnection.g3db");
        roadPropertiesToModels.put("3322", "TripleSecondRoadConnection.g3db");
    }

    public static RoadConnection makeRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end) {
        String roadProperties = "" + start.getLines() + "" + end.getLines() + "" + start.getLevel() + "" + end.getLevel();
        Model model = Starter.assetManager.get(roadPropertiesToModels.get(roadProperties), Model.class);
        boolean flip = (start.getLevel() == end.getLevel() && start.getLines() < end.getLines()) ||
            (start.getLevel() != end.getLevel() && start.getLevel() < end.getLevel());
        return new RoadConnection(roadGraph, start, end, model, flip);
    }
}
