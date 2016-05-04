package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
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
        paddle.interact(new FrameBrick(new Point(7, 8.9), new Size(50, 100)));
        assertTrue(paddle.getPosition().equals(point));

        Ball ball = new Ball();
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
        MyLifeLostListener lifeListener = new MyLifeLostListener();
        paddle.addLifeLostListener(lifeListener);
        assertFalse(lifeListener.lost);
        paddle.lifeLost();
        assertTrue(lifeListener.lost);
    }


    /** Test Method exchange/paddleListener */
    @Test
    public void testPaddleListener(){
        point = new Point(10, 7);
        paddle = new ExpandedPaddle(point);
        Speed speed = new Speed(270);
        speed.setLength(1);
        paddle.setSpeed(speed);
        MyPaddleChangedListener paddleListener = new MyPaddleChangedListener();
        paddle.addListener(paddleListener);
        paddle.setAlive(true);
        assertFalse(paddleListener.changed);
        paddle.move();
        paddle.exchange(paddle);
        assertFalse(paddle.isAlive());
        assertTrue(paddleListener.changed);
        assertEquals(paddle.getPosition(), new Point(10, 6));
    }

    /** Test Method fire */
    @Test
    public void testFire(){
        point = new Point(10, 7);
        paddle = new ExpandedPaddle(point);
        Room room = new Room();
        Beam beam = new Beam(point, room);
        paddle.setFirable(false);
        assertFalse(contains_beam(room.getGobjs(), (beam)));
        paddle.fire(room);
        assertFalse(contains_beam(room.getGobjs(), (beam)));
        paddle.setFirable(true);
        assertFalse(contains_beam(room.getGobjs(), (beam)));
        paddle.fire(room);
        assertTrue(contains_beam(room.getGobjs(), (beam)));
    }

    private boolean contains_beam(Set<Gobj> s, Gobj beam){
        for (Gobj o : s){
            if (o instanceof  Beam){
                if (beam.getPosition().equals(o.getPosition()))
                    return true;
            }
        }
        return false;
    }

}
