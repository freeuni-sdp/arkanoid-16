package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class KillerBrick extends FrameBrick {
    public KillerBrick(Point position, Size size) {

        super(position, size);
    }

    @Override
    public void interact(Gobj other) {

    }
}
