package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;


public class Ball extends Gobj {

    private static double RADIUS = (float) 0.5;

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
            Point c = getPosition();
            double radius = RADIUS;
            Point l = otherRectangle.getPosition();
            Point r = otherRectangle.getBottomRight();
            Size s = otherRectangle.getSize();
            boolean touchTop = Math.abs(c.Y - l.Y) <= radius && c.X >= l.X && c.X <= r.X + s.getWidth();
            boolean touchBottom = Math.abs(c.Y - r.Y) <= radius && c.X >= l.X && c.X <= r.X + s.getWidth();
            boolean touchLeft = Math.abs(c.X - l.X) <= radius && c.Y >= l.Y && c.Y <= r.Y + s.getHeight();
            boolean touchRight = Math.abs(c.X - r.X) <= radius && c.Y >= l.Y && c.Y <= r.Y + s.getHeight();

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
    public boolean isAlive() {
        return true;
    }

}
