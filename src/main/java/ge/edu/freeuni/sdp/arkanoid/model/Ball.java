package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;


public class Ball extends Gobj {

    private static double RADIUS = (float) 0.25;
    private Point _prevPosition;

    protected Ball(Point position) {

        super(position);
    }

    @Override
    public Shape getShape() {
        return new Circle(RADIUS, getPosition());
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Ball;
    }

    public void interact(Gobj other) {

    }

    public void bounceFrom(Rectangle rectangle) {
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
            if (touchTop) newSpeed = newSpeed.mirrorVertically();
            if (touchBottom) newSpeed = newSpeed.mirrorVertically();
            if (touchLeft) newSpeed = newSpeed.mirrorHorizontally();
            if (touchRight) newSpeed = newSpeed.mirrorHorizontally();

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
    }

    @Override
    public boolean isAlive() {
        return true;
    }

}
