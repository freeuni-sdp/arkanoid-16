/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author dato
 */
public class AutopilotCapsuleTest {
    
    @Mock private Point point;
    @Mock private Room room;
    @Mock private Paddle mockPaddle;
    
    private List<Gobj> balls;
    private int speedAngle;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        balls = new ArrayList<>();
        speedAngle = 30;
    }
    
    
    @Test
    public void capsuleCanBeKilled() {
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        assertTrue(capsule.isAlive());
    }
    
    @Test
    public void getShapeCorrectShape(){
        Capsule capsule = new AutopilotCapsule(point, room);
        Rectangle actualShape = capsule.getShape();
        Rectangle expectedShape = new Rectangle(capsule.getPosition(), new Size(1, 1));
        assertTrue(expectedShape.equals(actualShape));
    }
    
    @Test
    public void kindIsCapsule(){
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        assertEquals(GobjKind.Capsule, capsule.getKind());
    }
    
    @Test
    public void releaseCapsuleInRoom(){
        Capsule capsule = new AutopilotCapsule(point, room);
        capsule.release(point);
        verify(room).add(capsule);
    }
    
    @Test
    public void accessToRoom(){
        Capsule capsule = new AutopilotCapsule(point, room);
        assertEquals(room, capsule.getRoom());
    }
    
    @Test
    public void capsuleReleaseOnAppropriatePosition(){
        Capsule capsule = new AutopilotCapsule(point, room);
        Point position = new Point(10, 10);
        capsule.release(position);
        
        assertTrue(capsule.getPosition().equals(position));
    }
    
    @Test
    public void createCapsuleMethod() {
        Point otherPoint = new Point(1, 1);
        
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        AutopilotCapsule actualCapsule = (AutopilotCapsule)capsule.createCapsule(otherPoint, room);
        
        AutopilotCapsule expected = new AutopilotCapsule(otherPoint, room);
        
        assertTrue(expected.equals(actualCapsule));
    }
    
    @Test
    public void interactCapsule_callParentClassInteract(){
        Capsule capsule = new AutopilotCapsule(point, room);
        capsule.interact(mockPaddle);
        assertFalse(capsule.isAlive());
    }
    
    @Test
    public void interactCapsule_doesNotCallParentClassInteract(){
        Ball ball = mock(Ball.class);
        Capsule capsule = new AutopilotCapsule(point, room);
        capsule.interact(ball);
        assertTrue(capsule.isAlive());
    }
    
    @Test
    public void interactCapsule_gameWithoutBall(){
        addPaddleInsteadeOfBall();
        when(room.getGobjs()).thenReturn(new HashSet<>(balls));
        
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        capsule.interact(mockPaddle);
        
        Speed sp = new Speed(speedAngle);
        sp.setLength(sp.getLength() * 2);
        verify(balls.get(0), never()).setSpeed(sp);
    }
    
    private void addPaddleInsteadeOfBall(){
        Paddle p = mock(Paddle.class);
        balls.add(p);
    }
    
    @Test
    public void interactCapsule_BallDoubleSpeed() {
        addBallBySpecialSpeed();
        when(room.getGobjs()).thenReturn(new HashSet<>(balls));
        
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        capsule.interact(mockPaddle);
        
        Speed sp = new Speed(speedAngle);
        sp.setLength(sp.getLength() * 2);
        verify(balls.get(0)).setSpeed(sp);
    }
    
    private void addBallBySpecialSpeed(){
        Ball currentBall = mock(Ball.class);
        when(currentBall.getSpeed()).thenReturn(new Speed(speedAngle));
        balls.add(currentBall);
    }
    
    @Test
    public void paddleExchange(){
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        
        Paddle newPaddle = mock(Paddle.class);
        capsule.setNewPaddle(newPaddle);
        
        capsule.interact(mockPaddle);
        verify(mockPaddle).exchange(newPaddle);
    }
}
