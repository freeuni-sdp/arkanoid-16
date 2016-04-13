package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;

public abstract class Brick extends Gobj<Rectangle> {

    private final BrickColor _color;
    private final Capsule _capsule;
    private BrickColorStrategy _strategy;
    private boolean _isAlive;

    Brick(Point position, BrickColor color, Capsule capsule) {
        super(position);
        _color = color;
        _capsule = capsule;
        _isAlive = true;
        _strategy = BrickColorStrategy.getStrategy(_color);
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Brick;
    }

    public void interact(Gobj other) {
        if (_color == BrickColor.Gold) {
            return;
        }
        if (other instanceof Ball) {
            _capsule.release(getPosition());
            _isAlive = false;
        } else if (other instanceof MovementKillerBrick){
            _isAlive = false;
        }
    }

    public void setBrickColor(BrickColorStrategy strategy) {
        _strategy = strategy;
    }

    public int getScore() {
        return _strategy.getScore();
    }
    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public BrickColor getColor() {
        return _color;
    }
}
