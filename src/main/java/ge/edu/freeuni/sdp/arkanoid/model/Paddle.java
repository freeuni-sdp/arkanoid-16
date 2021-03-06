package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.DisruptionCapsuleInterfaces.IPlayer;
import ge.edu.freeuni.sdp.arkanoid.DisruptionCapsuleInterfaces.OriginalSoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.DisruptionCapsuleInterfaces.StubSoundPlayer;
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
    private boolean _isFirable;
    private IPlayer player = new OriginalSoundPlayer();

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

    Paddle(Point position, StubSoundPlayer player) {
        super(position);
        _isAlive = true;
        _paddleListeners = new HashSet<>();
        _lifeLostListeners = new HashSet<>();
        prevPosition = position;
        this.player = player;
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
            player.play(SoundPlayer.COLLIDE);
            setPosition(prevPosition);
        }

        if (other instanceof Ball) {
            Ball ball = (Ball) other;
            player.play(SoundPlayer.PARRY);
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

    public boolean isFirable() { return _isFirable; }

    public void setFirable(boolean isFirable) { _isFirable = isFirable; }

    void exchange(Paddle newPaddle) {
        _isAlive = false;
        newPaddle.setPosition(this.getPosition());
        for (PaddleChangedListener listener : _paddleListeners) {
            listener.paddleChanged(newPaddle);
        }
    }

    void fire(Room room) {
        if (_isFirable) {
            Beam beam = new Beam(getPosition(), room);
            room.add(beam);
        }
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
