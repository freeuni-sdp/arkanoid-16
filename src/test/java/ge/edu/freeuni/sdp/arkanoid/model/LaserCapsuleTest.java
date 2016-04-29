package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Koba on 29/04/2016.
 */
public class LaserCapsuleTest {

    @Mock Point point;
    @Mock Room room;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /*
        test getRoom method
     */
    @Test
    public void testGetRoom() {
        Capsule c = new LaserCapsule(point, room);
        //c = c.createCapsule(point, room);
        assertEquals(room, c.getRoom());
    }

    /*
        test getPosition method
     */
    @Test
    public void testGetPosition() {
        Capsule c = new LaserCapsule(point, room);
        //c = c.createCapsule(point, room);
        assertEquals(point, c.getPosition());
    }

    @Test
    public void testKind() {
        Capsule c = new LaserCapsule(point, room);
        //c = c.createCapsule(point, room)
        assertEquals(GobjKind.Capsule, c.getKind());
    }

    /*
        test if setFirable(true) is called once when
        capsule interacts with paddle instance
     */
    @Test
    public void testPaddleCollision() {
        Capsule c = new LaserCapsule(point, room);
        //c = c.createCapsule(point, room);
        Paddle paddle = mock(Paddle.class);
        c.interact(paddle);
        verify(paddle).setFirable(true);
    }

    /*
        test if setFirable(true) is not called when
        capsule interacts with something other than an
        instance of paddle, which is same as checking
        the if statement
     */
    @Test
    public void testNonPaddleCollision() {
        Capsule c = new LaserCapsule(point, room);
        //c = c.createCapsule(point, room);
        StubObj other = mock(StubObj.class);
        c.interact(other);
        verify(other, times(0)).setFirable(true);
    }

    // New class whose instance is not an instance of
    // Paddle and has setFirable() method
    private class StubObj extends Gobj {

        public StubObj(Point position) {
            super(position);
        }

        public void interact(Gobj other) {}

        public boolean isAlive() {
            return true;
        }

        public GobjKind getKind() {
            return GobjKind.Paddle;
        }
        public Rectangle getShape() {
            return new Rectangle(getPosition(), new Size(1, 1));
        }
        public void setFirable(boolean firable) {}
    }
}

