package proto.traffic.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import proto.traffic.game.cars.CarManager;
import proto.traffic.game.input.Adapter;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.obstacles.ObstacleGraph;
import proto.traffic.game.map.path.PathGraph;
import proto.traffic.game.map.roads.RoadConstructor;
import proto.traffic.game.map.roads.RoadDestructor;
import proto.traffic.game.map.roads.RoadGraph;
import proto.traffic.game.map.structures.BuildingManager;

public class GameScreen implements Screen {
    private final Environment environment;
    public static PerspectiveCamera cam;
    private final CameraInputController camController;
    private final ModelBatch modelBatch;

    private final RoadConstructor roadConstructor;
    private final RoadDestructor roadDestructor;

    private boolean destruction = false;

    private final MapGraph mapGraph;
    private final RoadGraph roadGraph = new RoadGraph(this);
    private final PathGraph pathGraph = new PathGraph();
    private final ObstacleGraph obstacleGraph = new ObstacleGraph(this);
    private final CarManager carManager;
    private final BuildingManager buildingManager;

    private int score = 0;
    private float budget = 10;

    public GameScreen () {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        mapGraph = new MapGraph();

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(-0f, 100f, -0f);
        cam.lookAt(-0f,0,-0f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        carManager = new CarManager(pathGraph);
        roadConstructor = new RoadConstructor(mapGraph, roadGraph, pathGraph, obstacleGraph, cam);
        roadDestructor = new RoadDestructor(roadGraph);
        buildingManager = new BuildingManager(this, carManager, roadGraph, mapGraph);
        camController = new CameraInputController(cam);

        Adapter inputProcessor = new Adapter(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(camController);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    public void setDestruction (boolean destruction) {
        this.destruction = destruction;
        roadConstructor.setLastRoadPieceAsNull();
    }

    public void increaseScore () {
        score += 10;
        increaseBudget();
    }

    public void decreaseScore (int delta) {
        score -= delta;
        if (score < 0) {
            score = 0;
        }
    }

    public void increaseBudget () {
        budget += 1;
    }

    public void decreaseBudget (float delta) {
        budget -= delta;
    }

    public float getBudget() {
        return budget;
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

    public void click (Vector2 point) {
        if (destruction) {
            roadDestructor.mouseTouchDown(point);
            return;
        }
        roadConstructor.mouseTouchDown(point);
    }

    public void addCar () {
        carManager.addCar();
    }

    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        camController.update();

        System.out.println(score + " " + budget);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        buildingManager.render();

        carManager.render(delta);

        modelBatch.begin(cam);

        roadGraph.show(modelBatch, environment);
        mapGraph.show(modelBatch, environment);
        pathGraph.show(modelBatch, environment);
        obstacleGraph.show(modelBatch, environment);
        carManager.show(modelBatch, environment);

        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
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

    @Override
    public void hide() {

    }
}
