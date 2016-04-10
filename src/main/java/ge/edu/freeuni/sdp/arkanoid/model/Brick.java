package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;

public abstract class Brick extends Gobj<Rectangle> {

    private final BrickColor _color;
    private final Capsule _capsule;
    private boolean _isAlive;

    Brick(Point position, BrickColor color, Capsule capsule) {
        super(position);
        _color = color;
        _capsule = capsule;
        _isAlive = true;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Brick;
    }

    public void interact(Gobj other) {
        if (other instanceof Ball) {
            _capsule.release(getPosition());
            _isAlive = false;
        } else if (other instanceof KillerBrick){
            _isAlive = false;
        }
    }


    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public BrickColor getColor() {
        return _color;
    }
}
