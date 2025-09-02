package proto.traffic.game.map.obstacles.data;

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
}
