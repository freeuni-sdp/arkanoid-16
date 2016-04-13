package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CapsuleFactory {

    private Map<CapsuleType, Capsule> capsuleRegistry;

    public static CapsuleFactory getInstance() {
        return new CapsuleFactory();
    }

    private CapsuleFactory() {
        capsuleRegistry = new HashMap<>();
        initCapsuleFactory();
    }

    private void initCapsuleFactory() {
        capsuleRegistry.put(CapsuleType.Null, new NullCapsule(null));
        capsuleRegistry.put(CapsuleType.Kill, new KillCapsule(null, null));
        capsuleRegistry.put(CapsuleType.Expand, new ExpandCapsule(null, null));
        capsuleRegistry.put(CapsuleType.SlowBall, new SlowBallCapsule(null, null));
        capsuleRegistry.put(CapsuleType.ExtraLive, new ExtraLiveCapsule(null, null));
        capsuleRegistry.put(CapsuleType.Autopilot, new AutopilotCapsule(null, null));
        capsuleRegistry.put(CapsuleType.Break, new BreakCapsule(null, null));
    }

    public void registerCapsuleType(CapsuleType capsuleType, Capsule instance) {
        capsuleRegistry.put(capsuleType, instance);
        System.out.println(capsuleRegistry.size());
    }

    public Capsule createCapsule(CapsuleType capsuleType, Point position, Room room) {
        return capsuleRegistry.get(capsuleType).createCapsule(position, room);
    }

    public Set<CapsuleType> getCapsuleTypes() {
        return capsuleRegistry.keySet();
    }
}
