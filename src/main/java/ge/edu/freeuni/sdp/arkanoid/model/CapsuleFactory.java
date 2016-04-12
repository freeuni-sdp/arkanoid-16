package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CapsuleFactory {

    private Map<CapsuleType, Capsule> capsuleRegistry;

    private static CapsuleFactory ourInstance = new CapsuleFactory();

    public static CapsuleFactory getInstance() {
        return ourInstance;
    }

    private CapsuleFactory() {
        capsuleRegistry = new HashMap<>();
    }

    public void registerCapsuleType(CapsuleType capsuleType, Capsule instance) {
        capsuleRegistry.put(capsuleType, instance);
    }

    public Capsule createCapsule(CapsuleType capsuleType, Point position, Room room) {
        return capsuleRegistry.get(capsuleType).createCapsule(position, room);
    }

    public Set<CapsuleType> getCapsuleTypes() {
        return capsuleRegistry.keySet();
    }
}
