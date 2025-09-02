package proto.traffic.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.screens.EditorScreen;
import proto.traffic.game.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Starter extends Game {
    public static final AssetManager assetManager = new AssetManager();
    public static boolean loading = true;

    @Override
    public void create() {
        assetManager.load("GrassTile.g3db", Model.class);
        assetManager.load("RedTractor.g3db", Model.class);
        assetManager.load("BlueTractor.g3db", Model.class);
        assetManager.load("GreenTractor.g3db", Model.class);
        assetManager.load("RoadTile.g3db", Model.class);
        assetManager.load("DoubleFirstRoadConnection.g3db", Model.class);
        assetManager.load("DoubleFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("DoubleSecondRoadConnection.g3db", Model.class);
        assetManager.load("DoubleToMonoFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("DoubleToMonoZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("DoubleToTripleFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("DoubleToTripleZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("DoubleZeroRoadConnection.g3db", Model.class);
        assetManager.load("DoubleZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("MonoFirstRoadConnection.g3db", Model.class);
        assetManager.load("MonoFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("MonoSecondRoadConnection.g3db", Model.class);
        assetManager.load("MonoToDoubleFirstRoadConnection.g3db", Model.class);
        assetManager.load("MonoToDoubleFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("MonoToDoubleSecondRoadConnection.g3db", Model.class);
        assetManager.load("MonoToDoubleZeroRoadConnection.g3db", Model.class);
        assetManager.load("MonoToDoubleZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("MonoToTripleFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("MonoToTripleZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("MonoZeroRoadConnection.g3db", Model.class);
        assetManager.load("MonoZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("TripleFirstRoadConnection.g3db", Model.class);
        assetManager.load("TripleFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("TripleSecondRoadConnection.g3db", Model.class);
        assetManager.load("TripleToDoubleFirstRoadConnection.g3db", Model.class);
        assetManager.load("TripleToDoubleFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("TripleToDoubleSecondRoadConnection.g3db", Model.class);
        assetManager.load("TripleToDoubleZeroRoadConnection.g3db", Model.class);
        assetManager.load("TripleToDoubleZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("TripleToMonoFirstRoadConnection.g3db", Model.class);
        assetManager.load("TripleToMonoFirstToSecondRoadConnection.g3db", Model.class);
        assetManager.load("TripleToMonoSecondRoadConnection.g3db", Model.class);
        assetManager.load("TripleToMonoZeroRoadConnection.g3db", Model.class);
        assetManager.load("TripleToMonoZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("TripleZeroRoadConnection.g3db", Model.class);
        assetManager.load("TripleZeroToFirstRoadConnection.g3db", Model.class);
        assetManager.load("Farm.g3db", Model.class);
        assetManager.load("Garden.g3db", Model.class);
        assetManager.load("CowFarm.g3db", Model.class);
        assetManager.load("Mill.g3db", Model.class);
        assetManager.load("FruitFactory.g3db", Model.class);
        assetManager.load("MilkFactory.g3db", Model.class);
        assetManager.load("ForestPiece.g3db", Model.class);
        assetManager.load("RiverPiece.g3db", Model.class);
        assetManager.load("RiverConnection.g3db", Model.class);
    }

    private void doneLoading() {
        loading = false;
        this.setScreen(new EditorScreen());
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
