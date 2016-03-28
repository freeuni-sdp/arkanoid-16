package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Paddle extends Gobj {

    private static boolean[][] _normalShape = {
            {true, true, true, true, true}
    };


    protected Paddle(Point position) {
        super(position);
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(3, 1));
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Paddle;
    }

    @Override
    public void interact(Gobj other) {
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    public void fire() {

    }
}
