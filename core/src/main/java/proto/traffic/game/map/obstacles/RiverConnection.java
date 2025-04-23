package proto.traffic.game.map.obstacles;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.constants.Constants;

public class RiverConnection {
    private RiverPiece start;
    private RiverPiece end;
    private ModelInstance instance;

    public RiverConnection (RiverPiece start, RiverPiece end, Model model) {
        this.start = start;
        this.end = end;

        Vector2 vector2 = new Vector2(this.start.getPosition().x - end.getPosition().x, this.start.getPosition().z - end.getPosition().z);

        Vector3 vector3 = new Vector3((this.start.getPosition().x + end.getPosition().x)/2,
            0, //(start.getPosition().y),
            (this.start.getPosition().z + end.getPosition().z)/2);

        float degrees = Math.round(vector2.angleDeg());

        instance = new ModelInstance(model);
        instance.transform.setToTranslation(vector3);
        instance.transform.rotate(new Vector3(0, 1, 0), (270 - degrees));
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }
}
