package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.util.HashSet;
import java.util.Set;

public class Paddle extends Gobj<Rectangle> {

    private final Set<PaddleChangedListener> _paddleListeners;
    private final Set<LifeLostListener> _lifeLostListeners;
    private boolean _isAlive;
    private int extraLive;
    private Point prevPosition;


    public Paddle(Size roomSize) {
        this(new Point(roomSize.getWidth() / 2, roomSize.getHeight() - 2));
    }

    Paddle(Point position) {
        super(position);
        _isAlive = true;
        _paddleListeners = new HashSet<>();
        _lifeLostListeners = new HashSet<>();
        prevPosition = position;
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle(getPosition(), new Size(5, 1));
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Paddle;
    }

    @Override
    public void interact(Gobj other) {
        if (other instanceof FrameBrick) {
            SoundPlayer.getInstance().play(SoundPlayer.COLLIDE);
            setPosition(prevPosition);
        }

        if (other instanceof Ball) {
            Ball ball = (Ball) other;
            SoundPlayer.getInstance().play(SoundPlayer.PARRY);
            Speed newSpeed = ball.getSpeed().mirrorY();
            ball.setSpeed(newSpeed);
        }
    }

    @Override
    public void move() {
        prevPosition = getPosition();
        super.move();
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public void setAlive(boolean isAlive){
        _isAlive = isAlive;
    }

    void exchange(Paddle newPaddle) {
        _isAlive = false;
        newPaddle.setPosition(this.getPosition());
        for (PaddleChangedListener listener : _paddleListeners) {
            listener.paddleChanged(newPaddle);
        }
    }

    void fire() {
        //TODO: Laser goes here
    }

    void lifeLost() {
        for (LifeLostListener listener: _lifeLostListeners) {
            listener.lifeLost();
        }
    }
    void addListener(PaddleChangedListener listener) {
        _paddleListeners.add(listener);
    }

    void addLifeLostListener(LifeLostListener listener){_lifeLostListeners.add(listener); }

    @Override
    void setPosition(Point position) {
        super.setPosition(position);
        prevPosition = position;
    }
}
