package proto.traffic.game.map.roads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import proto.traffic.game.Starter;

public class RoadFactory {
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
