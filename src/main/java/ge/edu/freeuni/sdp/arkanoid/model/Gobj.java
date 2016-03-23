package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.HashSet;
import java.util.Set;

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

    protected abstract boolean[][] getShape();

    public abstract GobjKind getKind();

    public Set<Point> getOccupied() {
        HashSet<Point> points = new HashSet<Point>();
        boolean[][] shape = getShape();
        for (int i = 0; i < shape.length; i++) {
            boolean[] shapeColumn = shape[i];
            for (int j = 0; j < shapeColumn.length; j++) {
                Boolean isOccupied = shapeColumn[j];
                if (isOccupied) points.add(_position.add(new Point(j, i)));
            }
        }
        return points;
    }

    public void move() {
        _position = _position.add(_speed);
    }

    public abstract void interactAt(Gobj other, Set<Point> intersection);

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
