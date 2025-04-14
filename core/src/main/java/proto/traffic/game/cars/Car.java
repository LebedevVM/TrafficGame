package proto.traffic.game.cars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Sphere;
import com.badlogic.gdx.utils.Queue;
import proto.traffic.game.constants.Constants;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.path.PathNode;

public class Car {
    private Queue<PathNode> pathQueue = new Queue<>();
    private final PathGraph pathGraph;

    private final Vector3 position;
    private final Vector3 displacement = new Vector3();
    private final Vector3 direction = new Vector3();

    private float speed = Constants.maxCarSpeed;
    private float currentSpeed = 0;
    private float acceleration = 1;

    private CarController carController;

    private PathNode goalNode;
    private PathNode currentNode;

    private ModelInstance instance;

    private Sphere sightSphere;
    private Sphere centerSphere;

    private float xyDegrees;
    private float xzDegrees;

    public Car (CarController carController, PathGraph pathGraph, PathNode currentNode, PathNode goalNode) {
        this.pathGraph = pathGraph;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.position = new Vector3(currentNode.getPosition());
        this.carController = carController;
        sightSphere = new Sphere(position, Constants.carSightRadius);
        centerSphere = new Sphere(position, Constants.carSightRadius);

        findPath();

        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("car.obj"));

        instance = new ModelInstance(model);
        instance.transform.setToTranslation(position);
        instance.transform.rotate(new Vector3(0,1,0), 45);

        instance.transform.scale(0.5f, 0.5f, 0.5f);
    }

    public void findPath () {
        GraphPath<PathNode> graphPath = pathGraph.findPath(currentNode, goalNode);
        pathQueue = new Queue<>();
        for (int i = 1; i < graphPath.getCount(); i++) {
            pathQueue.addLast(graphPath.get(i));
        }
        reachNextNode();
    }

    public void render (float delta) {
        checkNextNode();
        if (currentSpeed < speed) {
            currentSpeed += acceleration * delta;
        }
        if (currentSpeed > speed) {
            currentSpeed -= acceleration*3 * delta;
        }
        if (carController.checkCollision(this)) {
            speed = 0;
        }
        else {
            speed = Constants.maxCarSpeed;
        }

        displacement.set(direction).setLength(currentSpeed*delta);
        position.add(displacement);
        centerSphere.center.add(displacement);
        sightSphere.center.add(displacement);
    }

    public void show (ModelBatch batch, Environment environment) {
        instance.transform.setToTranslation(position);
        instance.transform.scale(0.5f, 0.5f, 0.5f);

        instance.transform.rotate(new Vector3(0,1,0), xzDegrees);
        instance.transform.rotate(new Vector3(0,0,1), xyDegrees);

        batch.render(instance, environment);
    }

    public void checkNextNode () {
        if (pathQueue.size > 0) {
            PathNode nextNode = pathQueue.first();
            nextNode.carCrossed();
            Vector3 nextPos = nextNode.getPosition();
            if (Vector3.dst(position.x, position.y, position.z, nextPos.x, nextPos.y, nextPos.z) < 1) {
                pathQueue.removeFirst();
                reachNextNode();
            }
        }
    }

    public void reachNextNode () {
        if (pathQueue.size != 0) {
            PathNode nextNode = pathQueue.first();
            direction.set(nextNode.getPosition());
            direction.sub(position);
            xyDegrees = (float) (Math.atan(direction.y / Math.sqrt(direction.x*direction.x + direction.z*direction.z)) * MathUtils.radiansToDegrees);
            xzDegrees = - (float) Math.atan2(direction.z, direction.x) * MathUtils.radiansToDegrees;
        }
    }

    public boolean checkCollision (Car car) {
//        return orientedBoundingBox.contains(car.sightPoint) && car != this;
        return car.sightSphere.overlaps(centerSphere) && car != this;
//        orientedBoundingBox.
    }

    public boolean checkCollision (Sphere sphere) {
        return sphere.overlaps(centerSphere);
    }
}
