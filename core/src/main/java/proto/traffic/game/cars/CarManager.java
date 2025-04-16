package proto.traffic.game.cars;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.collision.Sphere;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.PathNode;

public class CarManager {
    private final Array<Car> cars = new Array<>();

    private final PathGraph pathGraph;

    public CarManager(PathGraph pathGraph) {
        this.pathGraph = pathGraph;
    }

    public void addCar () {
        Car car = new Car(this, pathGraph, pathGraph.getFirstPathNode(), pathGraph.getLastPathNode());
        cars.add(car);
    }

    public void addCar (PathNode start, PathNode end) {
        Car car = new Car(this, pathGraph, start, end);
        cars.add(car);
    }

    public boolean isRouteAccessible (PathNode start, PathNode end) {
        return pathGraph.findPath(start, end).getCount() > 0;
    }

    public void render (float delta) {
        for (Car car : cars) {
            car.render(delta);
        }
    }

    public void show (ModelBatch batch, Environment environment) {
        for (Car car : cars) {
            car.show(batch, environment);
        }
    }

    public boolean checkCollision (Car car) {
        for (int i = 0; i < cars.size; i ++) {
            Car car1 = cars.get(i);
            if (car1.checkCollision(car)) {
                if (car1 != car) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCollision (Sphere sphere) {
        for (int i = 0; i < cars.size; i ++) {
            Car car = cars.get(i);
            if (car.checkCollision(sphere)) {
                return true;
            }
        }
        return false;
    }

    public PathGraph getPathGraph () {
        return pathGraph;
    }
}
