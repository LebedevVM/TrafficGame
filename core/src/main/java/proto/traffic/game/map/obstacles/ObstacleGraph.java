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
import proto.traffic.game.map.obstacles.data.ConnectionData;
import proto.traffic.game.map.obstacles.data.ObstacleData;
import proto.traffic.game.map.obstacles.data.TileData;
import proto.traffic.game.screens.GameScreen;

public class ObstacleGraph {
    private final ObjectMap<MapNode, ForestPiece> forestPieces = new ObjectMap<>();
    private final ObjectMap<MapNode, RiverPiece> riverPieces = new ObjectMap<>();
    private final Array<RiverConnection> riverConnections = new Array<>();

    private final MapGraph mapGraph;

    public ObstacleGraph (MapGraph mapGraph) {
        this.mapGraph = mapGraph;
    }

    private GameScreen gameScreen;

    public void setObstacleData (ObstacleData obstacleData) {
        forestPieces.clear();
        riverPieces.clear();
        riverConnections.clear();

        for (TileData forest : obstacleData.getForests()) {
            addForestPiece(mapGraph.checkZeroNodes(new Vector2(forest.getX(), forest.getY())));
        }
        for (TileData river : obstacleData.getRivers()) {
            addRiverPiece(mapGraph.checkZeroNodes(new Vector2(river.getX(), river.getY())));
        }
        for (ConnectionData riverConnection : obstacleData.getRiverConnections()) {
            connectRiverPieces(mapGraph, riverConnection.getFirstTilePosition(), riverConnection.getSecondTilePosition());
        }
    }

    public void connectRiverPieces (RiverPiece start, RiverPiece end) {
        RiverConnection riverConnection = new RiverConnection(start, end);
        addRiverConnection(riverConnection);
    }

    public void connectRiverPieces (MapGraph mapGraph, Vector2 start, Vector2 end) {
        connectRiverPieces(mapGraph.mouseClick(start, 0), mapGraph.mouseClick(end, 0));
    }

    public void connectRiverPieces (MapNode start, MapNode end) {
        RiverConnection riverConnection = new RiverConnection(riverPieces.get(start), riverPieces.get(end));
        addRiverConnection(riverConnection);
    }

    public void addRiverPiece (MapGraph mapGraph, int index) {
        MapNode mapNode = mapGraph.getMapNodeByIndex(index);
        RiverPiece riverPiece = new RiverPiece(mapNode);
        riverPieces.put(mapNode, riverPiece);
    }

    public void addRiverPiece (MapNode mapNode) {
        RiverPiece riverPiece = new RiverPiece(mapNode);
        riverPieces.put(mapNode, riverPiece);
    }

    public RiverPiece getRiverPiece (MapNode mapNode) {
        return riverPieces.get(mapNode);
    }

    public void removeForestPiece (MapNode mapNode) {
        if (forestPieces.containsKey(mapNode)) {
            forestPieces.remove(mapNode);
            if (gameScreen != null) {
                gameScreen.decreaseScore(30);
            }
        }
    }

    public void addForestPiece (MapNode mapNode) {
        ForestPiece forestPiece = new ForestPiece(mapNode);
        forestPieces.put(mapNode, forestPiece);
    }

    public void addForestPiece (MapGraph mapGraph, int index) {
        MapNode mapNode = mapGraph.getMapNodeByIndex(index);
        ForestPiece forestPiece = new ForestPiece(mapNode);
        forestPieces.put(mapNode, forestPiece);
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
        for (ForestPiece forestPiece : forestPieces.values().toArray()) {
            forestPiece.show(batch, environment);
        }
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public ObjectMap<MapNode, RiverPiece> getRiverPieces() {
        return riverPieces;
    }

    public ObjectMap<MapNode, ForestPiece> getForestPieces() {
        return forestPieces;
    }

    public Array<RiverConnection> getRiverConnections() {
        return riverConnections;
    }
}
