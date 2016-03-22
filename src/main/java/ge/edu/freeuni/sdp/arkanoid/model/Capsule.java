package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class Capsule extends Gobj {

    private static boolean[][] _shape = {
            {true, true}
    };

    protected Capsule(Point position) {
        super(position);
    }

    @Override
    protected boolean[][] getShape() {
        return _shape;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Capsule;
    }

    @Override
    public void interactAt(Gobj other, Set<Point> intersection) {

    }
}
