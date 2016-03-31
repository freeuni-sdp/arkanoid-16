package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.util.Set;

public class Game implements GameFacade, PaddleChangedListener {
    private final ScoreCounter _scoreCounter;
    private Room _room;
    private Size _size;
    private Paddle _paddle;
    private Ball _ball;

    public Game(Size size) {
        this._size = size;
        _scoreCounter = new ScoreCounter();
    }

    public void init(Level level) {
        _room = new Room();
        level.build(_room, _scoreCounter);
        Point position = new Point(_size.getWidth() / 2, _size.getHeight() - 2);
        paddleChanged(new Paddle(position));
        _ball = new Ball(new Point(position.X + 1, position.Y - 2));
        _ball.setSpeed(new Speed(-45));
        _room.add(_ball);
        SoundPlayer.getInstance().play(SoundPlayer.PARRY);
    }

    public void move(Direction direction) {
        _paddle.setSpeed(direction.toSpeed());
        _room.move();
        _room.interact();
        _room.removeZombies();
    }

    public void fire() {
        _paddle.fire();
    }

    public boolean isGameOver() {
        return !_ball.isAlive();
    }

    public Size getSize() {
        return _size;
    }

    public Set<Gobj> getGobjs() {
        return _room.getGobjs();
    }

    public void paddleChanged(Paddle newPaddle) {
        _paddle = newPaddle;
        _room.add(_paddle);
        _paddle.addListener(this);
    }

}
