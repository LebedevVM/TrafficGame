package proto.traffic.game.map.roads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

public class RoadFactory {
    public static RoadConnection makeZeroMonoRoadConnection (RoadPiece start, RoadPiece end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("zeroMonoRoadConnection.obj"));
        return new RoadConnection(start, end, model);
    }

    public static RoadConnection makeZeroToFirstMonoRoadConnection (RoadPiece start, RoadPiece end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("zeroToFirstMonoRoadConnection.obj"));
        return new RoadConnection(start, end, model);
    }

    public static RoadConnection makeFirstMonoRoadConnection (RoadPiece start, RoadPiece end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("firstMonoRoadConnection.obj"));
        return new RoadConnection(start, end, model);
    }
}
