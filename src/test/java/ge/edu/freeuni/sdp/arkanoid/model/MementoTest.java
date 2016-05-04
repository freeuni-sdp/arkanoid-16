/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author dato
 */
public class MementoTest {
    
    @Mock private GameState gameState;
    @Mock private Ball ball;
    @Mock private Paddle paddle;
    @Mock private ScoreCounter scCounter;
    @Mock private Size size;
    
    private StubGameStateWriter stubWriter;
    private Speed speed;
    private Point position;
    private int score;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stubWriter = new StubGameStateWriter();
        stubGameStateMethods();
        
        speed = new Speed(30);
        position = new Point(10, 10);
        score = 0;
    }

    private void stubGameStateMethods(){
        when(gameState.getBall()).thenReturn(ball);
        when(gameState.getPaddle()).thenReturn(paddle);
        when(gameState.getScoreCounter()).thenReturn(scCounter);
        when(gameState.getSize()).thenReturn(size);
    }
    
    @Test
    public void initialGameStateSaving_ballSpeed() {
        when(ball.getSpeed()).thenReturn(speed);
        
        Memento memento = new Memento(gameState, stubWriter);
        
        memento.initialGameState(ball, paddle, scCounter, size);
        Speed actualSpeed = stubWriter.getBallSpeed();
        assertTrue(ball.getSpeed().equals(actualSpeed));
    }
    
    @Test
    public void initialGameStateSaving_ballPosition(){
        when(ball.getPosition()).thenReturn(position);
        
        Memento memento = new Memento(gameState, stubWriter);
        
        memento.initialGameState(ball, paddle, scCounter, size);
        Point actualPosition = stubWriter.getBallPosition();
        assertTrue(ball.getPosition().equals(actualPosition));
    }
    
    @Test
    public void initialGameStateSaving_ballSpeedAngleDegrees(){
        when(ball.getSpeed()).thenReturn(speed);
        
        Memento memento = new Memento(gameState, stubWriter);
        
        memento.initialGameState(ball, paddle, scCounter, size);
        double actualDegrees = stubWriter.getBallSpeed().getAngleDegrees();
        assertEquals(ball.getSpeed().getAngleDegrees(), actualDegrees, 0);
    }
    
    @Test
    public void initialGameStateSaving_paddlePosition(){
        when(paddle.getPosition()).thenReturn(position);
        
        Memento memento = new Memento(gameState, stubWriter);
        
        memento.initialGameState(ball, paddle, scCounter, size);
        Point actualPosition = stubWriter.getPaddlePosition();
        assertTrue(paddle.getPosition().equals(actualPosition));
    }
    
    @Test
    public void initialGameStateSaving_score(){
        when(scCounter.getScore()).thenReturn(score);
        
        Memento memento = new Memento(gameState, stubWriter);
        
        memento.initialGameState(ball, paddle, scCounter, size);
        int actualScore = stubWriter.getScore();
        assertEquals(scCounter.getScore(), actualScore);
    }
    
    @Test
    public void initialGameStateSaving_size(){
        Memento memento = new Memento(gameState, stubWriter);
        
        memento.initialGameState(ball, paddle, scCounter, size);
        Size actualSize = stubWriter.getSizeInstance();
        assertTrue(actualSize.equals(size));
    }
}
