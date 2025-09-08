package proto.traffic.game.map.obstacles.data;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ConnectionData {
    private List<TileData> connection;

    public ConnectionData (Float x1, Float y1, Float x2, Float y2) {
        connection = new ArrayList<>();
        connection.add(new TileData(x1, y1));
        connection.add(new TileData(x2, y2));
    }

    public ConnectionData (List<TileData> connection) {
        this.connection = connection;
    }

    public List<TileData> getConnection() {
        return connection;
    }

    public void setConnection(List<TileData> connection) {
        this.connection = connection;
    }

    public Vector2 getFirstTilePosition () {
        return new Vector2(connection.get(0).getX(), connection.get(0).getY());
    }

    public Vector2 getSecondTilePosition () {
        return new Vector2(connection.get(1).getX(), connection.get(1).getY());
    }
}
