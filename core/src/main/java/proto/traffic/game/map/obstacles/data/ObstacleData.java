package proto.traffic.game.map.obstacles.data;

import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.map.obstacles.ForestPiece;
import proto.traffic.game.map.obstacles.RiverConnection;
import proto.traffic.game.map.obstacles.RiverPiece;

import java.util.ArrayList;
import java.util.List;

public class ObstacleData {
    private String name;
    private List<TileData> forests;
    private List<TileData> rivers;
    private List<ConnectionData> riverConnections;

    public ObstacleData () {
        forests = new ArrayList<>();
        rivers = new ArrayList<>();
        riverConnections = new ArrayList<>();
    }

    public ObstacleData (String name, List<TileData> forests, List<TileData> rivers, List<ConnectionData> riverConnections) {
        this.name = name;
        this.forests = forests;
        this.rivers = rivers;
        this.riverConnections = riverConnections;
    }

    public void addForest (ForestPiece forestPiece) {
        Vector3 position = forestPiece.getMapNode().getPosition();
        forests.add(new TileData(position.x, position.z));
    }

    public void addRiverPiece (RiverPiece riverPiece) {
        Vector3 position = riverPiece.getMapNode().getPosition();
        rivers.add(new TileData(position.x, position.z));
    }

    public void addRiverConnection (RiverConnection riverConnection) {
        Vector3 startPosition = riverConnection.getStart().getPosition();
        Vector3 endPosition = riverConnection.getEnd().getPosition();
        riverConnections.add(new ConnectionData(startPosition.x, startPosition.z, endPosition.x, endPosition.z));
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<TileData> getForests () {
        return forests;
    }

    public void setForests (List<TileData> forests) {
        this.forests = forests;
    }

    public List<TileData> getRivers () {
        return rivers;
    }

    public void setRivers (List<TileData> rivers) {
        this.rivers = rivers;
    }

    public List<ConnectionData> getRiverConnections () {
        return riverConnections;
    }

    public void setRiverConnections (List<ConnectionData> riverConnections) {
        this.riverConnections = riverConnections;
    }
}
