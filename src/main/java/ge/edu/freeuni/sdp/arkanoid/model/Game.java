package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.Set;

public class Game implements GameFacade, PaddleChangedListener {
    private final ScoreCounter _scoreCounter;
    private final LiveCounter _liveCounter;
    private final Size _size;
    private Room _room;
    private Paddle _paddle;
    private Ball _ball;


    public Game(Size size) {
        this._size = size;
        _scoreCounter = new ScoreCounter();
        _liveCounter = new LiveCounter();

    }

    public void init(Level level) {
        _room = new Room();

        level.build(_room, _scoreCounter);
        Paddle newPaddle = new Paddle(_size);
        paddleChanged(newPaddle);
        _ball = new Ball();
        _ball.putOn(newPaddle);

        _room.add(_ball);
        _room.setLiveCounter(_liveCounter);
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

    @Override
    public void pause() {
        SoundPlayer.getInstance().play(SoundPlayer.PAUSE);
        //TODO  save state
    }

    public boolean isGameOver() {
        return !_ball.isAlive();
    }

    public boolean isLevelCleared() {
        return _room != null && _room.areKillablesLeft();
    }

    public Size getSize() {
        return _size;
    }

    public Set<Gobj> getGobjs() {
        return _room.getGobjs();
    }

    @Override
    public int geLives() {
       return  _room.getLives();
    }

    public void paddleChanged(Paddle newPaddle) {
        _paddle = newPaddle;
        _room.add(_paddle);
        _paddle.addListener(this);
    }

}
