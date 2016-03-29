package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class NullCapsule extends Capsule {
    public NullCapsule(Point position) {
        super(position, null);
    }

    @Override
    public void release(Point position) {
        //DO nothing
    }
}
