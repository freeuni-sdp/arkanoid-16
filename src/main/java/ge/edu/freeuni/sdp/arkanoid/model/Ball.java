package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Circle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;


public class Ball extends Gobj<Circle> {

    private Point _prevPosition;

    public Ball() {
        this(new Point(0, 0));
    }

    private Ball(Point position) {
        this(position, new Speed(-45));
    }

    private Ball(Point position, Speed speed) {
        super(position, speed);
    }

    @Override
    public Circle getShape() {
        double RADIUS = (float) 0.5;
        return new Circle(RADIUS, getPosition());
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Ball;
    }

    public void interact(Gobj other) {

    }

    void bounceFrom(Rectangle rectangle) {
        Point leftTop = rectangle.getPosition();
        Point rightBottom = rectangle.getBottomRight();

        Point previous = _prevPosition;
        Point current = getPosition();

        while (rectangle.canOverlap(current)) {
            boolean touchTop = previous.Y <= leftTop.Y && current.Y >= leftTop.Y;
            boolean touchBottom = previous.Y >= rightBottom.Y && current.Y <= rightBottom.Y;
            boolean touchLeft = previous.X <= leftTop.X && current.X >= leftTop.X;
            boolean touchRight = previous.X >= rightBottom.X && current.X <= rightBottom.X;

            Speed newSpeed = getSpeed();
            if (touchTop) newSpeed = newSpeed.mirrorY();
            if (touchBottom) newSpeed = newSpeed.mirrorY();
            if (touchLeft) newSpeed = newSpeed.mirrorX();
            if (touchRight) newSpeed = newSpeed.mirrorX();

            if (touchTop || touchBottom || touchLeft || touchRight) {
                SoundPlayer.getInstance().play(SoundPlayer.BOUNCE);
                setSpeed(newSpeed);
            }
            current = previous.add(newSpeed);
        }
    }

    @Override
    public void move() {
        _prevPosition = getPosition();
        super.move();
        smoothenCoordinates();
    }

    private void smoothenCoordinates() {
        setPosition(getPosition().smoothen(getSpeed().getLength() / 2));
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    void putOn(Gobj<Rectangle> other) {
        Point p = other.getPosition();
        double width = other.getShape().getSize().getWidth();
        double radius = getShape().getRadius();
        Point position = new Point(p.X + width / 2, p.Y - radius);
        setPosition(position);
    }
}
