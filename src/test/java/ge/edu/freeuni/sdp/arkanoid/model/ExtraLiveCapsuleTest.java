package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.w3c.dom.css.Rect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by nino on 04.05.2016.
 */
public class ExtraLiveCapsuleTest {

    @Mock
    private Room room;
    @Mock
    private Point point;
    @Mock
    private Paddle paddle;
    @Mock
    private Ball ball;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCapsule() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        assertEquals(extraLiveCapsule.getPosition(), point);
        assertEquals(extraLiveCapsule.getRoom(), room);
    }

    @Test
    public void testIsKind() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        assertEquals(GobjKind.Capsule, extraLiveCapsule.getKind());
    }

    @Test
    public void testCapsuleShape() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        Rectangle rect = new Rectangle(point, new Size(1, 1));
        assertEquals(rect, extraLiveCapsule.getShape());
    }

    @Test
    public void livesAndDies() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        assertTrue(extraLiveCapsule.isAlive());

        when(room.getLiveCounter()).thenReturn(new LiveCounter());

        extraLiveCapsule.interact(paddle);
        assertFalse(extraLiveCapsule.isAlive());
    }

    @Test
    public void testInteractionWithNull() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        extraLiveCapsule.interact(null);
    }

    @Test
    public void testInteractionWithBall() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        LiveCounter liveCounter = new LiveCounter(3);

        when(room.getLiveCounter()).thenReturn(liveCounter);

        extraLiveCapsule.interact(ball);
        assertEquals(3, liveCounter.getLive());
    }

    @Test
    public void testLieIsIncreased() {
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, room);
        LiveCounter liveCounter = new LiveCounter(3);

        when(room.getLiveCounter()).thenReturn(liveCounter);

        extraLiveCapsule.interact(paddle);
        assertEquals(4, liveCounter.getLive());
    }

    @Test
    public void testCapsuleRelease() {
        Room testRoom = new Room();
        ExtraLiveCapsule extraLiveCapsule = new ExtraLiveCapsule(point, testRoom);
        extraLiveCapsule.release(point);
        assertTrue(testRoom.getGobjs().contains(extraLiveCapsule));
    }
}
