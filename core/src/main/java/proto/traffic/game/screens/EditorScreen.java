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
import proto.traffic.game.input.EditorScreenAdapter;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.obstacles.ObstacleConstructor;
import proto.traffic.game.map.obstacles.ObstacleGraph;

public class EditorScreen implements Screen {
    private final Environment environment;
    public static PerspectiveCamera cam;
    private final CameraInputController camController;
    private final ModelBatch modelBatch;

    private final MapGraph mapGraph;
    private final ObstacleGraph obstacleGraph;

    private final ObstacleConstructor obstacleConstructor;

    public EditorScreen () {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));
//        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, 0f, -1f, 0f));

        mapGraph = new MapGraph();
        obstacleGraph = new ObstacleGraph(mapGraph);

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(-0f, 100f, -0f);
        cam.lookAt(-0f,0,-0f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);

        EditorScreenAdapter inputProcessor = new EditorScreenAdapter(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(camController);

        obstacleConstructor = new ObstacleConstructor(cam, mapGraph, obstacleGraph);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void mouseDragged (Vector2 position) {
        obstacleConstructor.mouseDragged(position);
    }

    public void click (Vector2 point) {
        obstacleConstructor.mouseTouchDown(point);
    }

    public void changeForest () {
        obstacleConstructor.changeForest();
    }

    public void saveData () {
        obstacleConstructor.saveData();
    }

    @Override
    public void show () {

    }

    @Override
    public void render (float delta) {
        camController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        mapGraph.show(modelBatch, environment);
        obstacleGraph.show(modelBatch, environment);
        modelBatch.end();
    }

    @Override
    public void resize (int i, int i1) {

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose() {

    }
}
