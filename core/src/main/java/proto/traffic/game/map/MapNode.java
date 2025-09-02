package proto.traffic.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import proto.traffic.game.Starter;
import proto.traffic.game.constants.Constants;

public class MapNode {
    private Vector3 position;
    private Circle circle;
    private Circle rangeCircle;

    public AssetManager assets  = new AssetManager();


//    public Model model;
    public ModelInstance instance;

    private MapNodeTrio mapNodeTrio;

    private boolean isOccupiedByRoad = false;
    private boolean isOccupiedByObstacle = false;

    boolean loading = false;

    ModelBuilder modelBuilder;

    public MapNode (Vector3 position) {
        this.position = position;
        circle = new Circle(position.x, position.z, Constants.mapNodeDistance/2f);
        rangeCircle = new Circle(position.x, position.z, Constants.mapNodeDistance/1.5f);

        Model model = Starter.assetManager.get("GrassTile.g3db", Model.class);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(position);
//        instance.transform.rotate(new Vector3(0, 1, 0), 30);
//        instance.transform.scale(0.6f, 0.6f, 0.6f);
        this.instance.transform.scale(Constants.scale, Constants.scale, Constants.scale);
    }

    public boolean clicked (Vector2 click) {
        if (click == null) {
            return false;
        }

        return circle.contains(click);
    }

    public boolean isInRange (MapNode mapNode) {
        return this.rangeCircle.overlaps(mapNode.rangeCircle);
    }

    public void show (ModelBatch batch, Environment environment) {
        batch.render(instance, environment);
    }

    public Vector3 getPosition () {
        return position;
    }

    public boolean isOccupiedByRoad () {
        return isOccupiedByRoad;
    }

    public boolean isOccupiedByObstacle () {
        return isOccupiedByObstacle;
    }

    public boolean isOccupied () {
        return isOccupiedByObstacle || isOccupiedByRoad;
    }

    public void setOccupiedByObstacle (boolean occupiedByObstacle) {
        isOccupiedByObstacle = occupiedByObstacle;
    }

    public void setOccupiedByRoad (boolean occupiedByRoad) {
        isOccupiedByRoad = occupiedByRoad;
    }

    public MapNodeTrio getMapNodeTrio () {
        return mapNodeTrio;
    }

    public void setMapNodeTrio (MapNodeTrio mapNodeTrio) {
        this.mapNodeTrio = mapNodeTrio;
    }

    @Override
    public boolean equals (Object obj) {
        MapNode mapNode = (MapNode) obj;
        return mapNode.mapNodeTrio.equals(mapNodeTrio);
    }
}
