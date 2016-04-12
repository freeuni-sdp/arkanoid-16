package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class Game implements GameFacade, PaddleChangedListener, BallListener, LifeLostListener {

    private final ScoreCounter _scoreCounter;
    private final LiveCounter _liveCounter;
    private final Size _size;
    private Room _room;
    private Paddle _paddle;
    private Ball _ball;
    private boolean isPaused;


    public Game(Size size) {
        this._size = size;
        _scoreCounter = new ScoreCounter();
        _liveCounter = new LiveCounter();
    }


    private Ball getBall(){
        Set<Gobj> _gobjs =  _room.getGobjs();
        for (Gobj obj : _gobjs) {
            if (obj instanceof Ball) {
                return (Ball)obj;
            }
        }
        return null;
    }

    public void init(Level level) {
        _room = new Room();

        level.build(_room, _scoreCounter);
        Paddle newPaddle = new Paddle(_size);
        paddleChanged(newPaddle);
        _ball = getBall();
        if(_ball == null)
            _ball = new SpeedingBall();

        _ball.addListener(this);
        _ball.addBallDeadListener(this);
        _ball.putOn(newPaddle);
        _room.add(_ball);
        _room.setLiveCounter(_liveCounter);
        _room.setScoreCounter(_scoreCounter);
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
        if(isPaused) isPaused = false;
        else  isPaused = true;
        if(!isPaused){
            deleteStateFile();
            return;
        }else {
            GameState gameState = new GameState();
            gameState.setPaddle(_paddle);
            gameState.setSize(_size);
            gameState.setLevelIndex(Configuration.getInstance().getSelectedLevelIndex());
            gameState.setBall(_ball);
            gameState.setScoreCounter(_scoreCounter);
            Originator originator = new Originator();
            originator.set(gameState);
            GameStateTaker taker = new GameStateTaker();
            taker.saveMemento(originator.saveToMemento());
        }
    }

    private void deleteStateFile() {
        File file = new File("resources/state.properties");
        if(file.exists()) file.delete();
    }

    public boolean isGameOver() {
        return _liveCounter.getLive() == 0;
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
        _paddle.addLifeLostListener(this);
    }




    @Override
    public void ballDied(Ball ball) {

        if (ball.equals(_ball)) {
            ArrayList<Ball> balls = _room.getBalls();
            if (balls.size() > 1) {
                _ball = balls.get(1);
            }
        }

    }

    @Override
    public void ballAdded(Ball ball) {

        ball.addBallDeadListener(this);
        ball.addListener(this);
        _room.add(ball);
        ball.increaseNumBalls();


    }


    @Override
    public int getScore() {
        return _room.getScore();
    }

    @Override
    public void lifeLost(){
        _liveCounter.decrease();
        if(_liveCounter.getLive() != 0){
            _ball.putOn(_paddle);
            _room.add(_ball);
        }
    }
}
