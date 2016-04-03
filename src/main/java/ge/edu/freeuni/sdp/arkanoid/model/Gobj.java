package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.util.ArrayList;

public abstract class Gobj<TShape extends Shape> {

    private Point _position;
    private Speed _speed;
    private ArrayList<GobjDeathListener> _deathListeners;

    Gobj(Point position) {
        this(position, Speed.NULL);
    }

    Gobj(Point position, Speed speed) {
        _position = position;
        _speed = speed;
        _deathListeners = new ArrayList<>();
    }

    public abstract TShape getShape();

    public abstract GobjKind getKind();

    public void move() {
        _position = _position.add(_speed);
    }

    public abstract void interact(Gobj other);

    public Point getPosition() {
        return _position;
    }

    void setPosition(Point position) {
        _position = position;
    }

    Speed getSpeed() {
        return _speed;
    }

    void setSpeed(Speed _speed) {
        this._speed = _speed;
    }

    public void setDeathListener(GobjDeathListener listener) {
        _deathListeners.add(listener);
    }

    protected void notifyAllDeath(String reason) {
        for (GobjDeathListener l : _deathListeners) {
            l.gobjDied(this, reason);
        }
    }

    public abstract boolean isAlive();
}
