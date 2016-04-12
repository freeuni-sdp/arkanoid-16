package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Circle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Ball extends Gobj<Circle> {

    private static int _numBalls;

    private Point _prevPosition;

    private final Set<LifeLostListener> _listeners = new HashSet<LifeLostListener>();

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
        if(other instanceof KillerBrick){
            decreaseNumBalls();
            if(_numBalls == 0) {
                for (LifeLostListener listener : _listeners)
                    listener.lifeLost();
            }
        }
        else if (other instanceof Brick){
            Brick brick = (Brick) other;
            this.bounceFrom(brick.getShape());
        }
        if (other instanceof Ball){
            Ball ball = (Ball) other;
            this.bounceFrom(ball.getShape());
        }
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
            previous = current;
            current = previous.add(newSpeed);
        }
    }
    void bounceFrom(Circle circle) {

            Speed newSpeed = getSpeed();
            newSpeed = newSpeed.mirrorY();
            SoundPlayer.getInstance().play(SoundPlayer.BOUNCE);
            setSpeed(newSpeed);

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

    void addListener(LifeLostListener listener) {
        _listeners.add(listener);
    }

    public static int getNumBalls(){
        return _numBalls;
    }

    public static void increaseNumBalls(){
        _numBalls++;
    }

    public static void decreaseNumBalls(){
        if(_numBalls>=1)
            _numBalls--;
    }

    public static void setNumBalls(int num){
        _numBalls = num;
    }
}
