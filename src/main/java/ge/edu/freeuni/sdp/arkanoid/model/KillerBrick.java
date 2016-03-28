package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class KillerBrick extends SolidBrick {
    public KillerBrick(Point position) {
        super(position);
    }

    @Override
    public void interact(Gobj other) {
    }
}
