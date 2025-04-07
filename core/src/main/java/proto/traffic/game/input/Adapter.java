package proto.traffic.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import proto.traffic.game.Starter;

public class Adapter extends InputAdapter {
    Starter starter;

    public Adapter (Starter starter) {
        this.starter = starter;
    }

    @Override
    public boolean keyDown(int keycode) {
        starter.changeLevel();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        starter.hitSomething(new Vector2(screenX, screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        starter.mouseDragged(new Vector2(screenX, screenY));
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}
