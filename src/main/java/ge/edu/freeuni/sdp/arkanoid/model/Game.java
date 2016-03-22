package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class Game implements GameFacade {
    private Room _room;
    private Size _size;
    private Paddle _paddle;

    public Game(Size size) {

        this._size = size;
    }

    public void init(Level level) {
        _room = new Room();
        level.build(_room);
        Point position = new Point(_size.getWidth() / 2, _size.getHeight() - 2);
        _paddle = new Paddle(position);
    }

    public void move(Direction direction) {
        _paddle.setSpeed(direction.toSpeed());
    }

    public void fire() {
        _paddle.fire();
    }

    public boolean isGameOver() {
        return false;
    }

    public Size getSize() {
        return _size;
    }

    public Set<Gobj> getGobjs() {
        return _room.getGobjs();
    }
}
