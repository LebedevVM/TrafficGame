package proto.traffic.game.constants;

import com.badlogic.gdx.utils.ObjectMap;

public abstract class Constants {
    public static final int mapNodeDistance = 6;
    public static final int bridgeHeight = 2;
    public static final int roadRadius = 2;
    public static final int pathNodeDistance = 2;
    public static final int maxCarSpeed = 10;
    public static final int carAcceleration = 5;
    public static final float carRotationSpeed = 400;
    public static final float carSightRadius = 1f;
    public static final float carCrossedSignificance = 3;
    public static final float exportFrequency = 10;
    public static final float importFrequency = 10;
    public static final ObjectMap<Integer, Float> degreesToSpeedCoefficientMap = new ObjectMap<>();
    static {
        degreesToSpeedCoefficientMap.put(0, 1f);
        degreesToSpeedCoefficientMap.put(6, 0.8f);
        degreesToSpeedCoefficientMap.put(5, 0.8f);
        degreesToSpeedCoefficientMap.put(7, 0.8f);
        degreesToSpeedCoefficientMap.put(29, 0.8f);
        degreesToSpeedCoefficientMap.put(30, 0.8f);
        degreesToSpeedCoefficientMap.put(31, 0.8f);
        degreesToSpeedCoefficientMap.put(12, 0.6f);
        degreesToSpeedCoefficientMap.put(11, 0.6f);
        degreesToSpeedCoefficientMap.put(13, 0.6f);
        degreesToSpeedCoefficientMap.put(24, 0.6f);
        degreesToSpeedCoefficientMap.put(23, 0.6f);
        degreesToSpeedCoefficientMap.put(25, 0.6f);
    }
}
