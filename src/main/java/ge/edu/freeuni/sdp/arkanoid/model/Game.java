package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.Set;

public class Game implements GameFacade, PaddleChangedListener {
    private final ScoreCounter _scoreCounter;
    private final Size _size;
    private Room _room;
    private Paddle _paddle;
    private Ball _ball;
    private boolean _levelCleared;

    public Game(Size size) {
        this._size = size;
        _scoreCounter = new ScoreCounter();
    }

    public void init(Level level) {
        _room = new Room();
        _levelCleared = false;

        level.setLevelClearedListener(new LevelClearedListener() {
            @Override
            public void levelCleared() {
                _levelCleared = true;
            }
        });

        level.build(_room, _scoreCounter);
        Paddle newPaddle = new Paddle(_size);
        paddleChanged(newPaddle);
        _ball = new Ball();
        _ball.putOn(newPaddle);

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

    public boolean isLevelCleared() {
        return _levelCleared;
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
