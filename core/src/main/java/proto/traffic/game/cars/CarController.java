package proto.traffic.game.cars;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.map.path.PathGraph;

public class CarController {
    private Array<Car> cars = new Array<>();

    private PathGraph pathGraph;

    public CarController(PathGraph pathGraph) {
        this.pathGraph = pathGraph;
    }

    public void addCar () {
        Car car = new Car(pathGraph, pathGraph.getFirstPathNode(), pathGraph.getLastPathNode());
        cars.add(car);
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
}
