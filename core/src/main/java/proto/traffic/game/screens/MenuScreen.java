package proto.traffic.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import proto.traffic.game.map.MapGraph;
import proto.traffic.game.map.obstacles.ObstacleGraph;
import proto.traffic.game.map.obstacles.data.ObstacleData;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MenuScreen implements Screen {
    private final Environment environment;
    public static PerspectiveCamera cam;
    private final CameraInputController camController;
    private final ModelBatch modelBatch;

    private final MapGraph mapGraph;
    private final ObstacleGraph obstacleGraph;

    private Stage stage;

    private int mapIndex = 0;

    private final Array<ObstacleData> maps = new Array<>();

    public MenuScreen () {
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

        stage = new Stage();

        float worldWidth = Gdx.graphics.getWidth();
        float worldHeight = Gdx.graphics.getHeight();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = Color.BLACK;
        fontParameter.size = 50;
        fontParameter.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopkrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя1234567890/";

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontGenerator.generateFont(fontParameter);
        labelStyle.fontColor = Color.BLACK;

        Button leftButton = new Button(new TextureRegionDrawable(new Texture("leftButtonUp.png")), new TextureRegionDrawable(new Texture("leftButtonDown.png")));
        Button rightButton = new Button(new TextureRegionDrawable(new Texture("rightButtonUp.png")), new TextureRegionDrawable(new Texture("rightButtonDown.png")));
        Button startButton = new Button(new TextureRegionDrawable(new Texture("startButtonUp.png")), new TextureRegionDrawable(new Texture("startButtonDown.png")));
        Label label = new Label("plains", labelStyle);
        label.setAlignment(Align.center);

        leftButton.setSize(150, 150);
        rightButton.setSize(150, 150);
        startButton.setSize(300, 150);
        label.setSize(300, 150);

        leftButton.setPosition(50, worldHeight/2 - 75);
        rightButton.setPosition(worldWidth - 200, worldHeight/2 - 75);
        startButton.setPosition(worldWidth/2 - 150, worldHeight/3);
        label.setPosition(worldWidth/2 - 150, 2*worldHeight/3);

        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(startButton);
        stage.addActor(label);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(camController);
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);


        leftButton.setVisible(false);

        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mapIndex -= 1;
//                changeMap();
                if (mapIndex == 0) {
                    leftButton.setVisible(false);
                }
                if (mapIndex < maps.size - 2) {
                    rightButton.setVisible(true);
                }
            }
        });
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mapIndex += 1;
//                changeMap();
                if (mapIndex == maps.size - 1) {
                    rightButton.setVisible(false);
                }
                if (mapIndex > 0) {
                    leftButton.setVisible(true);
                }
            }
        });
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                startGame();
            }
        });
    }

    @Override
    public void show () {

    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act();
        stage.draw();
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

    @Override
    public void dispose() {

    }
}
