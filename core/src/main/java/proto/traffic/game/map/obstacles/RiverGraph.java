package proto.traffic.game.map.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.MapNode;

public class RiverGraph {
    private final ObjectMap<MapNode, RiverPiece> riverPieces = new ObjectMap<>();
    private final Array<RiverConnection> riverConnections = new Array<>();

    public void connectRiverPieces (RiverPiece start, RiverPiece end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("zeroMonoRoadConnection.obj"));
        RiverConnection riverConnection = new RiverConnection(start, end, model);
        addRiverConnection(riverConnection);
    }

    public void connectRiverPieces (MapGraph mapGraph, Vector2 start, Vector2 end) {
        connectRiverPieces(mapGraph.mouseClick(start, 0), mapGraph.mouseClick(end, 0));
    }

    public void connectRiverPieces (MapNode start, MapNode end) {
        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("zeroMonoRoadConnection.obj"));
        RiverConnection riverConnection = new RiverConnection(riverPieces.get(start), riverPieces.get(end), model);
        addRiverConnection(riverConnection);
    }

    public void addRiverPiece (MapGraph mapGraph, Vector2 vector2) {
        MapNode mapNode = mapGraph.mouseClick(vector2, 0);
        RiverPiece riverPiece = new RiverPiece(mapNode);
        riverPieces.put(mapNode, riverPiece);
    }

    public void addRiverPiece (MapNode mapNode) {
        RiverPiece riverPiece = new RiverPiece(mapNode);
        riverPieces.put(mapNode, riverPiece);
    }

    public void addRiverConnection (RiverConnection riverConnection) {
        riverConnections.add(riverConnection);
    }

    public void show (ModelBatch batch, Environment environment) {
        for (RiverPiece riverPiece : riverPieces.values().toArray()) {
            riverPiece.show(batch, environment);
        }
        for (RiverConnection riverConnection : riverConnections) {
            riverConnection.show(batch, environment);
        }
    }
}
