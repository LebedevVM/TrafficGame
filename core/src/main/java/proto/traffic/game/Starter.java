package proto.traffic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import proto.traffic.game.cars.CarController;
import proto.traffic.game.input.Adapter;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.roads.RoadConstructor;
import proto.traffic.game.map.roads.RoadDestructor;
import proto.traffic.game.map.roads.RoadGraph;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Starter extends ApplicationAdapter {
    public Environment environment;
    public static PerspectiveCamera cam;
    public CameraInputController camController;
    public ModelBatch modelBatch;
    public Model model;
    public Model model1;
    public ModelInstance instance;

    public Array<ModelInstance> instances = new Array<>();

    private RoadConstructor roadConstructor;
    private RoadDestructor roadDestructor;

    MapGraph mapGraph;
    private RoadGraph roadGraph = new RoadGraph();
    private PathGraph pathGraph = new PathGraph();
    private CarController carController;

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        mapGraph = new MapGraph();

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(-40f, 50f, -12.5f);
        cam.lookAt(-12.5f,0,-12.5f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        carController = new CarController(pathGraph);
        roadConstructor = new RoadConstructor(mapGraph, roadGraph, pathGraph, cam);
        roadDestructor = new RoadDestructor(roadGraph);

        camController = new CameraInputController(cam);

        Adapter inputProcessor = new Adapter(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(camController);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    boolean destruction = false;

    public void setDestruction (boolean destruction) {
        this.destruction = destruction;
        roadConstructor.setLastRoadPieceAsNull();
    }

    public void increaseLevel () {
        roadConstructor.increaseLevel();
    }

    public void decreaseLevel () {
        roadConstructor.decreaseLevel();
    }

    public void mouseDragged (Vector2 position) {
        if (destruction) {
            roadDestructor.mouseDragged(position);
            return;
        }
        roadConstructor.mouseDragged(position);
    }

    public void hitSomething (Vector2 screenCoords) {
        if (destruction) {
            roadDestructor.mouseTouchDown(screenCoords);
            return;
        }
        roadConstructor.mouseTouchDown(screenCoords);
    }

    public void addCar () {
        carController.addCar();
    }

    @Override
    public void render() {
        camController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        carController.render(Gdx.graphics.getDeltaTime());

        modelBatch.begin(cam);
//        modelBatch.render(instance, environment);

        for (ModelInstance modelInstance : instances) {
            modelBatch.render(modelInstance, environment);
        }
        roadGraph.show(modelBatch, environment);
        carController.show(modelBatch, environment);

        mapGraph.show(modelBatch, environment);
        pathGraph.show(modelBatch, environment);

        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
