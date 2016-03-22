package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class Brick extends Gobj {
    private static boolean[][] _shape = {
            {true, true}
    };
    private final BrickColor _color;

    public Brick(Point position, BrickColor color) {
        super(position);
        _color = color;
    }

    @Override
    protected boolean[][] getShape() {
        return _shape;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Brick;
    }

    @Override
    public void interactAt(Gobj other, Set<Point> intersection) {

    }

    public BrickColor getColor() {
        return _color;
    }
}
