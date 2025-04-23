package proto.traffic.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import proto.traffic.game.screens.GameScreen;

public class Adapter extends InputAdapter {
    GameScreen gameScreen;

    public Adapter (GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Input.Keys.SPACE == keycode) {
            gameScreen.addCar();
            return false;
        }
        if (Input.Keys.SHIFT_LEFT == keycode) {
            gameScreen.setDestruction(true);
            return false;
        }
        if (Input.Keys.UP == keycode) {
            gameScreen.increaseLevel();
            return false;
        }
        if (Input.Keys.DOWN == keycode) {
            gameScreen.decreaseLevel();
            return false;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (Input.Keys.SHIFT_LEFT == keycode) {
            gameScreen.setDestruction(false);
        }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gameScreen.click(new Vector2(screenX, screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        gameScreen.mouseDragged(new Vector2(screenX, screenY));
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}
