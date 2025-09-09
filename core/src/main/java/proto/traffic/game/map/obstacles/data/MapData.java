package proto.traffic.game.map.obstacles.data;

import java.util.ArrayList;
import java.util.List;

public class MapData {
    private List<String> address;

    public MapData () {
        address = new ArrayList<>();
    }

    public MapData (List<String> address) {
        this.address = address;
    }

    public void addAddress (String address) {
        this.address.add(address);
    }

    public List<String> getAddress () {
        return address;
    }

    public void setAddress (List<String> address) {
        this.address = address;
    }

    @Override
    public String toString () {
        return "MapData{" +
            "address=" + address +
            '}';
    }
}
