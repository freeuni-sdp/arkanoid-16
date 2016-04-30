/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import java.util.ArrayList;
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
    
    private List<Ball> balls;
    private int speedAngle;
    private Room realRoom;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        balls = new ArrayList<>();
        speedAngle = 30;
        realRoom = new Room();
    }
    
    @Test
    public void capsulCanBeKilled() {
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        assertTrue(capsule.isAlive());
    }
    
    @Test
    public void kindIsCapsule(){
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        assertEquals(GobjKind.Capsule, capsule.getKind());
    }
    
    @Test
    public void releaseCapsuleInRoom(){
        Capsule capsule = new AutopilotCapsule(point, realRoom);
        
        capsule.release(point);
        
        boolean contains = realRoom.getGobjs().contains(capsule);
        assertTrue(contains);
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
    public void interactCapsuleAndPaddle_KilledPaddle() {
        Paddle mockPaddle = mock(Paddle.class);
        AutopilotCapsule capsule = new AutopilotCapsule(point, room);
        capsule.interact(mockPaddle);
        assertFalse(mockPaddle.isAlive());
    }
    
    @Test
    public void interactCapsule_BallDoubleSpeed() {
        addBallBySpecialSpeed();
        when(room.getGobjs()).thenReturn(new HashSet<>(balls));
        
        Paddle mockPaddle = mock(Paddle.class);
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
    
}
