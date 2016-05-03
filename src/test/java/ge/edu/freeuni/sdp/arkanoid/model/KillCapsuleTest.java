package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by elene on 5/4/16.
 */
public class KillCapsuleTest {
    @Mock Room room;
    @Mock Point point;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstructor(){
        KillCapsule capsule = new KillCapsule(point, room);
        assertEquals(capsule.getRoom(), room);
        assertEquals(capsule.getPosition(), point);
    }

    @Test
    public void testGetKind(){
        KillCapsule capsule = new KillCapsule(point, room);
        assertEquals(capsule.getKind(), GobjKind.Capsule);
    }


    @Test
    public void testGetShape(){
        KillCapsule capsule = new KillCapsule(point, room);
        assertTrue(capsule.getShape().getClass() == Rectangle.class);
        Size size = capsule.getShape().getSize();
        assertEquals(size.getHeight(), 1);
        assertEquals(size.getWidth(), 1);
    }


    @Test
    public void testIsAlive(){
        KillCapsule capsule = new KillCapsule(point, room);
        assertTrue(capsule.isAlive());
    }

    @Test
    public void testReleasePosition(){
        Point releasePoint = mock(Point.class);
        KillCapsule capsule = new KillCapsule(point, room);
        capsule.release(releasePoint);
        assertEquals(capsule.getPosition(), releasePoint);
    }


    @Test
    public void testReleaseAddedToRoom(){
        Point releasePoint = mock(Point.class);
        KillCapsule capsule = new KillCapsule(point, room);
        capsule.release(releasePoint);
        verify(room).add(capsule);
    }

    @Test
    public void testInteractWithFrame(){
        KillCapsule capsule = new KillCapsule(point, room);
        FrameBrick frameBrick = mock(FrameBrick.class);
        capsule.interact(frameBrick);
        assertFalse(capsule.isAlive());
    }

    @Test
    public void testInteractWithPaddle(){
        KillCapsule capsule = new KillCapsule(point, room);
        Paddle paddle = mock(Paddle.class);
        capsule.interact(paddle);
        verify(paddle).lifeLost();
        assertFalse(capsule.isAlive());
    }

    @Test
    public void testInteractOther(){
        KillCapsule capsule = new KillCapsule(point, room);
        capsule.interact(mock(Gobj.class));
        assertTrue(capsule.isAlive());

    }


}
