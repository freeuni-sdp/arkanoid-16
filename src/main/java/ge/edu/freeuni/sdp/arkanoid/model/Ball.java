package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class Ball extends Gobj {

    private static boolean[][] _shape = {
            {true}
    };


    protected Ball(Point position) {
        super(position);
    }

    @Override
    protected boolean[][] getShape() {
        return _shape;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Ball;
    }

    @Override
    public void interactAt(Gobj other, Set<Point> intersection) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
