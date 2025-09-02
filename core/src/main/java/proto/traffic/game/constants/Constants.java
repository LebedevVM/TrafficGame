package proto.traffic.game.constants;

import com.badlogic.gdx.utils.ObjectMap;

public abstract class Constants {
    public static final float scale = 0.5f;
    public static final float mapNodeDistance = 22.5f*scale;
    public static final float bridgeHeight = 5*scale;
    public static final int roadRadius = 2;
    public static final int pathNodeDistance = 2;
    public static final float pathNodeRadius = 1.3f;
    public static final int maxCarSpeed = 10;
    public static final int carAcceleration = 5;
    public static final float carRotationSpeed = 400;
    public static final float carSightRadius = 1f;
    public static final float carCrossedSignificance = 3;
    public static final float exportFrequency = 10;
    public static final float importFrequency = 10;
    public static final float buildingFrequency = 10;
    public static final ObjectMap<Integer, Float> degreesToSpeedCoefficientMap = new ObjectMap<>();
    static {
        degreesToSpeedCoefficientMap.put(0, 1f);
        degreesToSpeedCoefficientMap.put(1, 1f);
        degreesToSpeedCoefficientMap.put(2, 1f);
        degreesToSpeedCoefficientMap.put(3, 1f);
        degreesToSpeedCoefficientMap.put(4, 1f);
        degreesToSpeedCoefficientMap.put(5, 0.8f);
        degreesToSpeedCoefficientMap.put(6, 0.8f);
        degreesToSpeedCoefficientMap.put(7, 0.8f);
        degreesToSpeedCoefficientMap.put(8, 0.6f);
        degreesToSpeedCoefficientMap.put(27, 0.6f);
        degreesToSpeedCoefficientMap.put(28, 0.6f);
        degreesToSpeedCoefficientMap.put(29, 0.6f);
        degreesToSpeedCoefficientMap.put(30, 0.6f);
        degreesToSpeedCoefficientMap.put(31, 0.6f);
        degreesToSpeedCoefficientMap.put(32, 0.6f);
        degreesToSpeedCoefficientMap.put(33, 0.6f);
    }
}
