package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vato on 5/3/16.
 */
public class AutopilotPaddleTest {

    @Mock private Point mockPoint;
    @Mock private Room mockRoom;
    @Mock private Capsule mockCapsule;
    @Mock private Ball mockBall;
    @Mock private Ball secondMockBall;
    @Mock private Point secondMockPoint;

    private Set<Gobj> ballList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Checks if speed of the paddle is positive
     */
    @Test
    public void speedIsPositiveTest() {
        ballList = new HashSet<>();
        ballList.add(mockBall);
        when(mockRoom.getGobjs()).thenReturn(ballList);
        // Create dummy point, because point.X and point.Y is not mockable
        Point dummyPoint = new Point(3, 3);
        when(mockBall.getPosition()).thenReturn(mockPoint);
        when(mockPoint.getX()).thenReturn(15.0);
        AutopilotPaddle paddle = new AutopilotPaddle(dummyPoint, 5000, mockCapsule, mockRoom);
        paddle.setSpeed(null);
        assertEquals("Positive speed test", 1.0, paddle.getSpeed().getX(), 0.001);
    }

    @Test
    public void speedIsNegativeTest() {
        ballList = new HashSet<>();
        ballList.add(mockBall);
        when(mockRoom.getGobjs()).thenReturn(ballList);
        // Create dummy point, because point.X and point.Y is not mockable
        Point dummyPoint = new Point(3, 3);
        when(mockBall.getPosition()).thenReturn(mockPoint);
        when(mockPoint.getX()).thenReturn(-20.0);
        AutopilotPaddle paddle = new AutopilotPaddle(dummyPoint, 5000, mockCapsule, mockRoom);
        paddle.setSpeed(null);
        assertEquals("Negative speed test", -1.0, paddle.getSpeed().getX(), 0.001);
    }

    @Test
    public void speedIsZeroTest() {
        ballList = new HashSet<>();
        ballList.add(mockBall);
        when(mockRoom.getGobjs()).thenReturn(ballList);
        // Create dummy point, because point.X and point.Y is not mockable
        Point dummyPoint = new Point(3, 3);
        when(mockBall.getPosition()).thenReturn(mockPoint);
        when(mockPoint.getX()).thenReturn(5.0);
        AutopilotPaddle paddle = new AutopilotPaddle(dummyPoint, 5000, mockCapsule, mockRoom);
        paddle.setSpeed(null);
        assertEquals("Negative speed test", 0.0, paddle.getSpeed().getX(), 0.001);
    }

    @Test
    public void multipleBallTest() {
        // Create dummy point, because point.X and point.Y is not mockable
        ballList = new HashSet<>();
        Point dummyPoint = new Point(3, 3);
        Point ballOneLocation = new Point(4, 4);
        Point ballTwoLocation = new Point(20, 20);
        when(mockBall.getPosition()).thenReturn(ballOneLocation);
        when(secondMockBall.getPosition()).thenReturn(ballTwoLocation);
        ballList.add(secondMockBall);
        ballList.add(mockBall);
        when(mockRoom.getGobjs()).thenReturn(ballList);
        AutopilotPaddle paddle = new AutopilotPaddle(dummyPoint, 5000, mockCapsule, mockRoom);
        paddle.setSpeed(null);
        assertEquals("Multiple ball speed test", 0.0, paddle.getSpeed().getX(), 0.001);
    }



}
