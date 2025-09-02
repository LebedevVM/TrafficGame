package proto.traffic.game.map.obstacles.data;

public class TileData {
    private Float x;
    private Float y;

    public TileData (Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX () {
        return x;
    }

    public Float getY () {
        return y;
    }

    public void setX (Float x) {
        this.x = x;
    }

    public void setY (Float y) {
        this.y = y;
    }
}
