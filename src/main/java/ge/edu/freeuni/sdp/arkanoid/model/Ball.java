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

        if (other instanceof Paddle) {
            SoundPlayer.getInstance().play(SoundPlayer.PARRY);
            Speed newSpeed = getSpeed().mirrorVertically();
            setSpeed(newSpeed);
            return;
        }

        if (other instanceof Brick) {
            Rectangle otherRectangle = (Rectangle) other.getShape();
            Point l = otherRectangle.getPosition();
            Point r = otherRectangle.getBottomRight();

            Point c = getPosition();
            Point p = _prevPosition;

            boolean touchTop = p.Y <= l.Y && c.Y >= l.Y;
            boolean touchBottom = p.Y >= r.Y && c.Y <= r.Y;
            boolean touchLeft = p.X <= l.X && c.X >= l.X;
            boolean touchRight = p.X >= r.X && c.X <= r.X;

            Speed newSpeed = getSpeed();
            if (touchTop) newSpeed = newSpeed.mirrorVertically();
            if (touchBottom) newSpeed = newSpeed.mirrorVertically();
            if (touchLeft) newSpeed = newSpeed.mirrorHorizontally();
            if (touchRight) newSpeed = newSpeed.mirrorHorizontally();

            if (touchTop || touchBottom || touchLeft || touchRight) {
                SoundPlayer.getInstance().play(SoundPlayer.BOUNCE);
                setSpeed(newSpeed);
            }
            return;
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
