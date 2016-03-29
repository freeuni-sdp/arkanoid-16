package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;

public abstract class Capsule extends Gobj {

    private final Room _room;
    private boolean _isAlive;

    protected Capsule(Point position, Room room) {

        super(position);
        _isAlive = true;
        _room = room;
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(1, 1));
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Capsule;
    }

    @Override
    public void interact(Gobj other) {
        if (!(other instanceof Ball)) {
            _isAlive = false;
        }
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public void release(Point position) {
        this.setPosition(position);
        Speed falling = new Speed(90);
        this.setSpeed(falling);
        _room.add(this);
    }

    protected Room getRoom() {
        return _room;
    }
}

