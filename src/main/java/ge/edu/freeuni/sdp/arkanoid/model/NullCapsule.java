package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class NullCapsule extends Capsule {

    static {
        CapsuleFactory.getInstance().registerCapsuleType(CapsuleType.Null, new NullCapsule());
    }

    private NullCapsule() {
        super(null, null);
    }

    public NullCapsule(Point position) {
        super(position, null);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new NullCapsule(position);
    }

    @Override
    public void release(Point position) {
        //DO nothing
    }
}
