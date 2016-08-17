package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;


/**
 * Created by Tornike on 04.05.2016.
 */
public class ExpandedPaddleTest {
    private ExpandedPaddle paddle;
    private Point point;
    private Random rand;
    private static final int EXPANDED_WIDTH = 9;
    private static final int EXPANDED_HEIGHT = 1;

    @Before
    public void setup() {
        rand = new Random();
        rand.setSeed(0);
    }

    /** Test Method getShape */
    @Test
    public void testGetShape(){
        for (int i = 0; i < 5; i++) {
            point = new Point(rand.nextDouble(), rand.nextDouble());
            paddle = new ExpandedPaddle(point);
            assertTrue(paddle.getShape().equals(new Rectangle(point, new Size(EXPANDED_WIDTH, EXPANDED_HEIGHT))));
        }
        point = new Point(0, 0);
        paddle = new ExpandedPaddle(point);
        assertTrue(paddle.getShape().equals(new Rectangle(point, new Size(EXPANDED_WIDTH, EXPANDED_HEIGHT))));
    }

    /** Test Method getKind */
    @Test
    public void testGetKind(){
        point = new Point(3, 2.5);
        paddle = new ExpandedPaddle(point);
        assertEquals(paddle.getKind(), GobjKind.Paddle);
    }

    /** Test Method interact */
    @Test
    public void testInteract(){
        point = new Point(5, 0);
        paddle = new ExpandedPaddle(point);
        paddle.setSpeed(new Speed(60));
        paddle.move();
        paddle.interact(mock(FrameBrick.class));
        assertTrue(paddle.getPosition().equals(point));

        Ball ball = spy(Ball.class);
        ball.setSpeed(new Speed(60));
        paddle.interact(ball);
        assertEquals(ball.getSpeed().getAngleDegrees(), new Speed(60).mirrorY().getAngleDegrees(), 1e-6);
        assertEquals(ball.getSpeed().getLength(), new Speed(60).getLength(), 1e-6);
    }

    /** Test Method Move */
    @Test
    public void testMove(){
        point = new Point(5, 0);
        paddle = new ExpandedPaddle(point);
        Speed speed = new Speed(90);
        speed.setLength(1);
        paddle.setSpeed(speed);
        paddle.move();
        assertTrue(paddle.getPosition().equals(new Point(5, 1)));
    }

    /** Test Method get/set alive */
    @Test
    public void testAlive(){
        point = new Point(5, 0);
        paddle = new ExpandedPaddle(point);
        paddle.setAlive(true);
        assertEquals(paddle.isAlive(), true);
        paddle.setAlive(false);
        assertEquals(paddle.isAlive(), false);
    }

    /** Test Method get/set firable */
    @Test
    public void testFirable(){
        point = new Point(5, 0);
        paddle = new ExpandedPaddle(point);
        paddle.setFirable(true);
        assertEquals(paddle.isFirable(), true);
        paddle.setFirable(false);
        assertEquals(paddle.isFirable(), false);
    }


    /** Test Method get/set position */
    @Test
    public void testPosition(){
        point = new Point(5, 0);
        paddle = new ExpandedPaddle(point);
        assertEquals(paddle.getPosition(), point);
        paddle.setPosition(new Point(7, 6));
        assertEquals(paddle.getPosition(), new Point(7, 6));
    }

    /** Test Method addLifelost/lifelost */
    @Test
    public void testLifeLost(){
        point = new Point(6, 7);
        paddle = new ExpandedPaddle(point);
        LifeLostListener lifeListener = mock(LifeLostListener.class);
        paddle.addLifeLostListener(lifeListener);
        paddle.lifeLost();
        verify(lifeListener, atLeastOnce()).lifeLost();
    }


    /** Test Method exchange/paddleListener */
    @Test
    public void testPaddleListener(){
        point = new Point(10, 7);
        paddle = new ExpandedPaddle(point);
        Speed speed = new Speed(270);
        speed.setLength(1);
        paddle.setSpeed(speed);
        PaddleChangedListener paddleListener = mock(PaddleChangedListener.class);
        paddle.addListener(paddleListener);
        paddle.setAlive(true);
        paddle.move();
        paddle.exchange(paddle);
        assertFalse(paddle.isAlive());
        verify(paddleListener, atLeastOnce()).paddleChanged(any());
        assertEquals(paddle.getPosition(), new Point(10, 6));
    }

    /** Test Method fire */
    @Test
    public void testFire(){
        point = mock(Point.class);
        paddle = new ExpandedPaddle(point);
        Room room = mock(Room.class);
        paddle.setFirable(false);
        paddle.fire(room);
        verify(room, never()).add(any());
        paddle.setFirable(true);
        paddle.fire(room);
        verify(room, times(1)).add(isA(Beam.class));
    }
}
