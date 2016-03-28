package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Capsule extends Gobj {

    protected Capsule(Point position) {
        super(position);
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(1, 1));
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Capsule;
    }

    public void interact(Gobj other) {

    }


    @Override
    public boolean isAlive() {
        return true;
    }
}
