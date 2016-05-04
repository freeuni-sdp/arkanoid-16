package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;


/**
 * Created by nino on 04.05.2016.
 */
public class PaddleTest {

    @Mock
    private Point point;

    private Paddle paddle;
    private Ball ball;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        paddle = new Paddle(point);
        ball = new Ball();
    }

    @Test
    public void testPaddleShape() {
        Rectangle rect = new Rectangle(point, new Size(5, 1));
        assertEquals(rect, paddle.getShape());
    }

    @Test
    public void testIsPaddleKind() {
        assertEquals(GobjKind.Paddle, paddle.getKind());
    }

    @Test
    public void testInteractionWithNull() {
        paddle.interact(null);
    }

    @Test
    public void testInteractionWithWall() {
        // Should set to previous position
        FrameBrick frameBrick = new FrameBrick(point, new Size(100, 100));
        paddle.interact(frameBrick);
        assertEquals(point, paddle.getPosition());
    }

    @Test
    public void testInteractionWithBall() {
        // After interacting with ball, ball's direction should change.
        Speed testSpeed = ball.getSpeed().mirrorY();
        paddle.interact(ball);
        assertEquals(testSpeed, ball.getSpeed());
    }

    @Test
    public void testIsAlive() {
        assertTrue(paddle.isAlive());
    }

    @Test
    public void testFirablePaddle() {
        Room room = new Room();
        LaserCapsule lazerCapsule = new LaserCapsule(point, room);
        lazerCapsule.interact(paddle);
        assertTrue(paddle.isFirable());
    }

    @Test
    public void testFire() {
        Room room = new Room();
        LaserCapsule lazerCapsule = new LaserCapsule(point, room);
        lazerCapsule.interact(paddle);

        int objectCounter = room.getGobjs().size();
        paddle.fire(room);
        assertEquals(objectCounter + 1, room.getGobjs().size());
    }

    @Test
    public void testLifeListeners() {
        LifeLostListener listener = mock(LifeLostListener.class);
        paddle.addLifeLostListener(listener);
        paddle.lifeLost();
        verify(listener).lifeLost();
    }

}
