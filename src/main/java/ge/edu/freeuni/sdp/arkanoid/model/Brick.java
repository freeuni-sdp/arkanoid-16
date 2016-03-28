package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Brick extends Gobj {

    private final BrickColor _color;

    public Brick(Point position, BrickColor color) {
        super(position);
        _color = color;
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(3, 1));
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Brick;
    }

    public void interact(Gobj other) {

    }


    @Override
    public boolean isAlive() {
        return true;
    }

    public BrickColor getColor() {
        return _color;
    }
}
