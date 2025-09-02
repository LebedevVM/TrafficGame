package proto.traffic.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import proto.traffic.game.screens.EditorScreen;
import proto.traffic.game.screens.GameScreen;

public class EditorScreenAdapter extends InputAdapter {
    EditorScreen editorScreen;

    public EditorScreenAdapter (EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Input.Keys.SHIFT_LEFT == keycode) {
            editorScreen.changeForest();
            return false;
        }
        if (Input.Keys.ALT_LEFT == keycode) {
            editorScreen.saveData();
            return false;
        }

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        editorScreen.click(new Vector2(screenX, screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        editorScreen.mouseDragged(new Vector2(screenX, screenY));
        return false;
    }
}
