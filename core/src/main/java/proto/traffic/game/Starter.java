package proto.traffic.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Starter extends Game {
    public static final AssetManager assetManager = new AssetManager();
    public static boolean loading = true;

    @Override
    public void create() {
        assetManager.load("GrassTile.g3db", Model.class);
        assetManager.load("RoadTile.g3db", Model.class);
        assetManager.load("MonoZeroRoadConnection.g3db", Model.class);
        assetManager.load("MonoToDoubleZeroRoadConnection.g3db", Model.class);
        assetManager.load("DoubleZeroRoadConnection.g3db", Model.class);
        assetManager.load("TripleZeroRoadConnection.g3db", Model.class);
        assetManager.load("TripleToDoubleZeroRoadConnection.g3db", Model.class);
        assetManager.load("TripleToMonoZeroRoadConnection.g3db", Model.class);
        assetManager.load("MonoZeroRoadTile.g3db", Model.class);
        assetManager.load("DoubleZeroRoadTile.g3db", Model.class);
    }

    private void doneLoading() {
        loading = false;
        this.setScreen(new GameScreen());
    }

    @Override
    public void render() {
        if (loading && assetManager.update()) {
            doneLoading();
        }
        if (loading) {
            return;
        }
        super.render();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
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
