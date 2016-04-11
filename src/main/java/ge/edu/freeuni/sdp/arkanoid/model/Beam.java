package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;

public abstract class Beam extends Gobj<Rectangle> {

    private boolean _isAlive;

    Beam(Point position) {
        super(position);
        _isAlive = true;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Beam;
    }

    public void interact(Gobj other) {

    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

}
