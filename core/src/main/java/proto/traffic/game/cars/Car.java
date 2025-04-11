package proto.traffic.game.cars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
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

    private PathNode goalNode;
    private PathNode currentNode;

    ModelInstance instance;

    public Car (PathGraph pathGraph, PathNode currentNode, PathNode goalNode) {
        this.pathGraph = pathGraph;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.position = new Vector3(currentNode.getPosition());
        findPath();


        ModelLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("car.obj"));

        instance = new ModelInstance(model);
        instance.transform.setToTranslation(position);
    }

    public void findPath () {
        GraphPath<PathNode> graphPath = pathGraph.findPath(currentNode, goalNode);
        pathQueue = new Queue<>();
        for (int i = 1; i < graphPath.getCount(); i++) {
            pathQueue.addLast(graphPath.get(i));
        }
        PathNode nextNode = pathQueue.first();
        direction.set(nextNode.getPosition());
        direction.sub(position);
    }

    public void render (float delta) {
        checkNextNode();
        if (currentSpeed < speed) {
            currentSpeed += acceleration * delta;
        }
        if (currentSpeed > speed) {
            currentSpeed -= acceleration * delta;
        }
        displacement.set(direction).setLength(currentSpeed*delta);
        position.add(displacement);
    }

    public void show (ModelBatch batch, Environment environment) {
        instance.transform.setToTranslation(position);
        batch.render(instance, environment);
    }

    public void checkNextNode () {
        if (pathQueue.size > 0) {
            PathNode nextNode = pathQueue.first();
            Vector3 nextPos = nextNode.getPosition();
            if (Vector3.dst(position.x, position.y, position.z, nextPos.x, nextPos.y, nextPos.z) < 0.1) {
                position.set(nextPos);
                reachNextNode();
            }
        }
    }

    public void reachNextNode () {
        pathQueue.removeFirst();

        if (pathQueue.size != 0) {
            PathNode nextNode = pathQueue.first();
            direction.set(nextNode.getPosition());
            direction.sub(position);
        }
    }
}
