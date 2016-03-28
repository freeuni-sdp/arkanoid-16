package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

public abstract class Gobj {

    private Point _position;
    private Speed _speed;

    protected Gobj(Point position) {
        this(position, Speed.NULL);
    }

    protected Gobj(Point position, Speed speed) {
        _position = position;
        _speed = speed;
    }

    public abstract Shape getShape();

    public abstract GobjKind getKind();

    public void move() {
        _position = _position.add(_speed);
    }

    public abstract void interact(Gobj other);

    public Point getPosition() {
        return _position;
    }

    public void setPosition(Point position) {
        _position = position;
    }

    public Speed getSpeed() {
        return _speed;
    }

    public void setSpeed(Speed _speed) {
        this._speed = _speed;
    }

    public abstract boolean isAlive();
}
