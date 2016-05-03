package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * Created by misho on 5/1/2016.
 */
public class BreakCapsuleTest {
    @Mock private Room room;

    private Point point;
    private BreakCapsule capsule;
    private Set<Gobj> stream;
    private Size size;
    private Paddle pd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        stream = new HashSet<Gobj>();
        capsule = new BreakCapsule(point, room);
        point = new Point(0, 0);
        size = new Size(10, 10);
        pd = new Paddle(point);
    }

    @Test
    public void testCreateCapsule(){
        Capsule testCapsule = capsule.createCapsule(point, room);
        assertEquals(testCapsule.getRoom(), room);
        assertEquals(testCapsule.getPosition(), point);
    }

    @Test
    public void testInteractWithNull(){
        // shouldnt happen any error
        capsule.interact(null);
    }

    @Test
    public void testInteractWithFrameBrick(){
        FrameBrick br = new FrameBrick(null, null);
        capsule.interact(br);
        assertFalse(capsule.isAlive());
    }

    @Test
    public void testInteractWithPaddleNoMovingBricks(){
        stream.clear();
        stream.add(new FrameBrick(point, size)); //not killable
        stream.add(new FrameBrick(point, size)); //not killable
        stream.add(new Ball()); // killable
        countDeadAndAlive(0, 3);


    }

    @Test
    public void testInteractWithPaddle(){
        stream.clear();
        stream.add(new FrameBrick(point, size)); //not killable
        MovingBrick killableOne = new MovingBrick(point,
                BrickColor.Blue,
                new NullCapsule(point),
                Direction.DOWN,
                0);
        MovingBrick killableTwo = new MovingBrick(point,
                BrickColor.Blue,
                new NullCapsule(point),
                Direction.DOWN,
                0);
        stream.add(killableOne);           // killable
        stream.add(killableTwo);           // killable
        stream.add(new Ball());
        countDeadAndAlive(2, 2);
    }

    private void countDeadAndAlive(int dead, int alive){
        when(room.getGobjs()).thenReturn(stream);
        capsule.interact(pd);
        int deadCounter = 0;
        int aliveCounter = 0;
        for (Gobj elem: stream) {
            if (elem.isAlive())
                aliveCounter++;
            else deadCounter++;
        }
        assertEquals(dead, deadCounter);
        assertEquals(alive, aliveCounter);
    }

    @Test
    public void testGetShape(){
        Rectangle result = capsule.getShape();
        assertEquals(capsule.getPosition(), result.getPosition());
        Size sz = result.getSize();
        assertTrue(sz.getHeight() == 1 && sz.getWidth() == 1);
    }

    @Test
    public void testGetKind(){
        assertEquals(GobjKind.Capsule, capsule.getKind());
    }

    @Test
    public void testGetRoom(){
        assertEquals(room, capsule.getRoom());
    }

    @Test
    public void testReleaseNull(){
        capsule.release(null);
    }

    @Test
    public void testReleasePosition(){
        Point p = new Point(10, 3);
        capsule.release(p);
        assertEquals(p, capsule.getPosition());
    }

    @Test
    public void testReleasePutObjInRoom(){
        capsule.release(point);
        verify(room).add(capsule);
    }
}
