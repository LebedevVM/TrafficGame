package proto.traffic.game.cars;

import com.badlogic.gdx.graphics.g3d.Model;
import proto.traffic.game.Starter;
import proto.traffic.game.map.path.PathNode;
import proto.traffic.game.map.structures.nodes.ImportNode;

public class CarFactory {
    public static Car createCar (CarManager carManager, PathNode start, PathNode end, ImportNode importNode, String name) {
        if (name.equals("farm")) {
            return createRedCar(carManager, start, end, importNode);
        }
        if (name.equals("wood")) {
            return createGreenCar(carManager, start, end, importNode);
        }
        if (name.equals("cow")) {
            return createBlueCar(carManager, start, end, importNode);
        }
        return null;
    }

    public static Car createRedCar (CarManager carManager, PathNode start, PathNode end, ImportNode importNode) {
        return new Car(carManager, carManager.getPathGraph(), start, end, importNode, Starter.assetManager.get("RedTractor.g3db", Model.class));
    }

    public static Car createBlueCar (CarManager carManager, PathNode start, PathNode end, ImportNode importNode) {
        return new Car(carManager, carManager.getPathGraph(), start, end, importNode, Starter.assetManager.get("BlueTractor.g3db", Model.class));
    }

    public static Car createGreenCar (CarManager carManager, PathNode start, PathNode end, ImportNode importNode) {
        return new Car(carManager, carManager.getPathGraph(), start, end, importNode, Starter.assetManager.get("GreenTractor.g3db", Model.class));
    }
}
