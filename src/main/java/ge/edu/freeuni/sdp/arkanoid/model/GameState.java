package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import ge.edu.freeuni.sdp.arkanoid.presenter.CellContent;

/**
 * Created by root_pc on 4/11/2016.
 */

public class GameState {

    private Ball ball;
    private Paddle paddle;
    private Size size;
    private CellContent[][] savePosition;
    private int levelIndex;
    private ScoreCounter scoreCounter;
    private boolean existState;


    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }

    public void setScoreCounter(ScoreCounter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }


    public int getLevelIndex() {
        return levelIndex;
    }

    public void setLevelIndex(int levelIndex){
        this.levelIndex = levelIndex;
    }

    public boolean isExistState() {
        return existState;
    }

    public void setExistState(boolean existState) {
        this.existState = existState;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }


    public CellContent[][] getSavePosition() {
        return savePosition;
    }

    public void setSavePosition(CellContent[][] savePosition) {
        this.savePosition = savePosition;
    }
}
