package proto.traffic.game.cars;

import com.badlogic.gdx.physics.bullet.collision.*;

public class CarCollisionChecker {
    static btCollisionConfiguration collisionConfig = new btDefaultCollisionConfiguration();
    static btDispatcher dispatcher = new btCollisionDispatcher(collisionConfig);
    static btCollisionAlgorithmConstructionInfo ci = new btCollisionAlgorithmConstructionInfo();

    public static boolean checkCollision (btCollisionObject ballObject, btCollisionObject groundObject) {
        CollisionObjectWrapper co0 = new CollisionObjectWrapper(ballObject);
        CollisionObjectWrapper co1 = new CollisionObjectWrapper(groundObject);

        ci.setDispatcher1(dispatcher);
        btCollisionAlgorithm algorithm = new btSphereBoxCollisionAlgorithm(null, ci, co0.wrapper, co1.wrapper, false);

        btDispatcherInfo info = new btDispatcherInfo();
        btManifoldResult result = new btManifoldResult(co0.wrapper, co1.wrapper);

        algorithm.processCollision(co0.wrapper, co1.wrapper, info, result);

        boolean r = result.getPersistentManifold().getNumContacts() > 0;

        result.dispose();
        info.dispose();
        algorithm.dispose();
        co1.dispose();
        co0.dispose();

        return r;
    }
}
