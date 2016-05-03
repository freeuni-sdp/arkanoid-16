package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Nikoloz on 05/04/16.
 */
public class CapsuleTest {
    @Mock Room room;
    @Mock Point pos;
    @Mock Paddle paddle;
    @Mock FrameBrick frameBrick;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNotNull_true() {
        Capsule caps = Mockito.mock(Capsule.class, Mockito.CALLS_REAL_METHODS);
        assertNotEquals(null, caps);
    }

    @Test
    public void testIsKillable() {
        Capsule caps = Mockito.mock(Capsule.class);
        assertFalse(caps.isKillable());
    }

    @Test
    public void testIsAliv() {
        Capsule caps = Mockito.mock(Capsule.class);
        assertFalse(caps.isAlive());
    }

    @Test
    public void testGetKind() {
        Capsule caps = Mockito.mock(Capsule.class, Mockito.CALLS_REAL_METHODS);
        assertEquals(GobjKind.Capsule, caps.getKind());
    }

    @Test
    public void testIsAlive() {
        Capsule caps = Mockito.mock(Capsule.class, Mockito.CALLS_REAL_METHODS);
        when(caps.isAlive()).thenReturn(true);
        caps.createCapsule(pos, room);
        assertEquals(caps.isAlive(), true);
    }


    @Test
    public void testShape() {
        Capsule caps = Mockito.mock(Capsule.class, Mockito.CALLS_REAL_METHODS);
        when(caps.getPosition()).thenReturn(pos);
        Rectangle tmp = new Rectangle(pos, new Size(1,1));
        assertEquals(tmp, caps.getShape());
    }

    @Test
    public void testReleasePosition() {
        Capsule caps = new TestCapsule(pos, room);
        caps.release(pos);
        assertEquals(pos, caps.getPosition());
    }

    @Test
    public void testReleaseSpeed() {
        Capsule caps = new TestCapsule(pos, room);
        caps.release(pos);
        assertEquals(new Speed(90), caps.getSpeed());
    }

    @Test
    public void testInteractNull() {
        Capsule caps = new TestCapsule(pos, room);
        caps.interact(null);
        assertEquals(true, caps.isAlive());
    }

    @Test
    public void testInteractPaddle() {
        Capsule caps = new TestCapsule(pos, room);
        caps.interact(paddle);
        assertEquals(false, caps.isAlive());
    }

    @Test
    public void testInteractCapsule() {
        Capsule caps = new TestCapsule(pos, room);
        caps.interact(Mockito.mock(Capsule.class));
        assertEquals(true, caps.isAlive());
    }

    @Test
    public void testInteractFrameBrick() {
        Capsule caps = new TestCapsule(pos, room);
        caps.interact(frameBrick);
        assertEquals(false, caps.isAlive());
    }



    private class TestCapsule extends Capsule {

        TestCapsule(Point position, Room room) {
            super(position, room);
        }

        @Override
        public Capsule createCapsule(Point position, Room room) {
            return new TestCapsule(position, room);
        }
    }

}
