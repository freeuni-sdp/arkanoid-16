/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 *
 * @author dato
 */
public class StubGameStateWriter implements GameStateWriter {

    private Ball ball;
    private Paddle paddle;
    private ScoreCounter scores;
    private Size size;
    
    @Override
    public void saveGameState(Ball ball, Paddle paddle, ScoreCounter scoreCounter, Size size) {
        this.ball = ball;
        this.paddle = paddle;
        scores = scoreCounter;
        this.size = size;
    }
    
    public Speed getBallSpeed(){
        return ball.getSpeed();
    }
    
    public Point getBallPosition(){
        return ball.getPosition();
    }
    
    public Point getPaddlePosition(){
        return paddle.getPosition();
    }
    
    public Size getPaddleSize(){
        return paddle.getShape().getSize();
    }
    
    public int getScore(){
        return scores.getScore();
    }
    
    public Size getSizeInstance(){
        return size;
    }
}
