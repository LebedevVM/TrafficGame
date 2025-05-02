package proto.traffic.game.map.roads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
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

    public static RoadConnection makeRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end, boolean a) {
        String roadProperties = "" + start.getLines() + "" + end.getLines() + "" + start.getLevel() + "" + end.getLevel();
        Model model = Starter.assetManager.get(roadPropertiesToModels.get(roadProperties), Model.class);
        boolean flip = false;
        if (start.getLevel() == end.getLevel()) {
            flip = start.getLines() < end.getLines();
        }
        else {
            flip = start.getLevel() < end.getLevel();
        }
        return new RoadConnection(roadGraph, start, end, model, flip);
    }

    public static RoadConnection makeRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end) {
        if (start.getLevel() != end.getLevel()) {
            if (start.getLevel() == 0) {
                if (end.getLevel() == 1) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(roadGraph, start, end, true);
                }
                if (end.getLevel() == 2) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(roadGraph, start, end, true);
                }
            }
            if (start.getLevel() == 2) {
                if (end.getLevel() == 1) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(roadGraph, start, end, false);
                }
                if (end.getLevel() == 0) {
                    return RoadFactory.makeFirstToSecondMonoRoadConnection(roadGraph, start, end, true);
                }
            }
            if (start.getLevel() == 1) {
                if (end.getLevel() == 0) {
                    return RoadFactory.makeZeroToFirstMonoRoadConnection(roadGraph, start, end, false);
                }
                if (end.getLevel() == 2) {
                    return RoadFactory.makeFirstToSecondMonoRoadConnection(roadGraph, start, end, true);
                }
            }
        }
        if (start.getLevel() == 0) {
            return RoadFactory.makeZeroRoadConnection(roadGraph, start, end);
        }
        if (start.getLevel() == 1) {
            return RoadFactory.makeFirstMonoRoadConnection(roadGraph, start, end);
        }
        if (start.getLevel() == 2) {
            return RoadFactory.makeSecondMonoRoadConnection(roadGraph, start, end);
        }
        return null;
    }

    public static RoadConnection makeZeroRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end) {
        Model model = null;
        boolean flip = start.getLines() < end.getLines();
        if (start.getLines() == 1) {
            if (end.getLines() == 1) {
                model = Starter.assetManager.get("MonoZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 2) {
                model = Starter.assetManager.get("MonoToDoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 3) {
                model = Starter.assetManager.get("TripleToMonoZeroRoadConnection.g3db", Model.class);
            }
        }
        if (start.getLines() == 2) {
            if (end.getLines() == 1) {
                model = Starter.assetManager.get("MonoToDoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 2) {
                model = Starter.assetManager.get("DoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 3) {
                model = Starter.assetManager.get("TripleToDoubleZeroRoadConnection.g3db", Model.class);
            }
        }
        if (start.getLines() == 3) {
            if (end.getLines() == 1) {
                model = Starter.assetManager.get("TripleToMonoZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 2) {
                model = Starter.assetManager.get("TripleToDoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 3) {
                model = Starter.assetManager.get("TripleZeroRoadConnection.g3db", Model.class);
            }
        }
        return new RoadConnection(roadGraph, start, end, model, flip);
    }

    public static RoadConnection makeZeroToFirstRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end) {
        Model model = null;
        boolean flip = start.getLines() < end.getLines();
        if (start.getLines() == 1) {
            if (end.getLines() == 1) {
                model = Starter.assetManager.get("MonoZeroToFirstRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 2) {

                model = Starter.assetManager.get("MonoToDoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 3) {
                model = Starter.assetManager.get("TripleToMonoZeroRoadConnection.g3db", Model.class);
            }
        }
        if (start.getLines() == 2) {
            if (end.getLines() == 1) {
                model = Starter.assetManager.get("MonoToDoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 2) {
                model = Starter.assetManager.get("DoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 3) {
                model = Starter.assetManager.get("TripleToDoubleZeroRoadConnection.g3db", Model.class);
            }
        }
        if (start.getLines() == 3) {
            if (end.getLines() == 1) {
                model = Starter.assetManager.get("TripleToMonoZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 2) {
                model = Starter.assetManager.get("TripleToDoubleZeroRoadConnection.g3db", Model.class);
            }
            if (end.getLines() == 3) {
                model = Starter.assetManager.get("TripleZeroRoadConnection.g3db", Model.class);
            }
        }
        return new RoadConnection(roadGraph, start, end, model, flip);
    }

    public static RoadConnection makeZeroToFirstMonoRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end, boolean flip) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("zeroToFirstMonoRoadConnection.obj"));
        return new RoadConnection(roadGraph, start, end, model, flip);
    }

    public static RoadConnection makeFirstMonoRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("firstMonoRoadConnection.obj"));
        return new RoadConnection(roadGraph, start, end, model, false);
    }

    public static RoadConnection makeFirstToSecondMonoRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end, boolean flip) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("firstToSecondMonoRoadConnection.obj"));
        return new RoadConnection(roadGraph, start, end, model, flip);
    }

    public static RoadConnection makeSecondMonoRoadConnection (RoadGraph roadGraph, RoadPiece start, RoadPiece end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("secondMonoRoadConnection.obj"));
        return new RoadConnection(roadGraph, start, end, model, false);
    }
}
