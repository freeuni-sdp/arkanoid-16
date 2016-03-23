package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class Paddle extends Gobj {

    private static boolean[][] _normalShape = {
            {true, true, true}
    };


    protected Paddle(Point position) {
        super(position);
    }

    @Override
    protected boolean[][] getShape() {
        return _normalShape;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Paddle;
    }

    @Override
    public void interactAt(Gobj other, Set<Point> intersection) {
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    public void fire() {

    }
}
