package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class FrameBrick extends Brick {
    private final Size _size;

    public FrameBrick(Point position, Size size) {
        super(position, BrickColor.Gold, new NullCapsule(position));
        _size = size;
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), _size);
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void interact(Gobj other) {
        super.interact(other);

        if (other instanceof Paddle) {
            Point paddlePosition = other.getPosition();
            Size paddleSize = ((Rectangle) other.getShape()).getSize();

            Point barrierPosition = this.getPosition();

            if (barrierPosition.X < 0) {
                paddlePosition.X = 0;
            } else {
                paddlePosition.X = barrierPosition.X - paddleSize.getWidth();
            }
        }
    }
}
