package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.HashSet;
import java.util.Set;

public class Paddle extends Gobj {

    private final Set<PaddleChangedListener> _listeners;
    private boolean _isAlive;

    protected Paddle(Point position) {
        super(position);
        _isAlive = true;
        _listeners = new HashSet<PaddleChangedListener>();
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(3, 1));
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Paddle;
    }

    @Override
    public void interact(Gobj other) {

    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public void exchange(Paddle newPaddle) {
        _isAlive = false;
        newPaddle.setPosition(this.getPosition());
        for (PaddleChangedListener listener : _listeners) {
            listener.paddleChanged(newPaddle);
        }
    }

    public void fire() {

    }

    public void addListener(PaddleChangedListener listener) {
        _listeners.add(listener);
    }
}
