package ge.edu.freeuni.sdp.arkanoid.model;


import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ScrollingWallGeneratorFrameBrickTest {

    @Mock Point position;
    @Mock Room room;
    @Mock Size size;
    @Mock Configuration mConfiguration;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Configuration.setInstanceForTesting(mConfiguration);
    }


    @Test
    public void generator_is_alive(){
        ScrollingWallGeneratorFrameBrick leftFrame = new ScrollingWallGeneratorFrameBrick(position, size, room);
        assertTrue(leftFrame.isAlive());
    }

    @Test
    public void generator_is_unkillable(){
        ScrollingWallGeneratorFrameBrick leftFrame = new ScrollingWallGeneratorFrameBrick(position, size, room);
        assertFalse(leftFrame.isKillable());
    }

    @Test
    public void generator_called_at_least_once(){
        ScrollingWallGeneratorFrameBrick leftFrame = new ScrollingWallGeneratorFrameBrick(position, size, room);
        leftFrame.move();
        verify(room, atLeast(1)).add(any());
    }


    @Test
    public void generator_called_right_brick(){
        ScrollingWallGeneratorFrameBrick leftFrame = new ScrollingWallGeneratorFrameBrick(position, size, room);
        leftFrame.move();
        ArgumentCaptor<Brick> brickCaptor = ArgumentCaptor.forClass(Brick.class);
        verify(room, atLeast(1)).add(brickCaptor.capture());

        List<Brick> capturedBricks = brickCaptor.getAllValues();
        assertEquals(capturedBricks.get(0).getClass(), ScrollingBrick.class);
    }

    @Test
    public void generator_called_right_coord(){
        ScrollingWallGeneratorFrameBrick leftFrame = new ScrollingWallGeneratorFrameBrick(position, size, room);
        leftFrame.move();
        ArgumentCaptor<Brick> brickCaptor = ArgumentCaptor.forClass(Brick.class);
        verify(room, atLeast(1)).add(brickCaptor.capture());

        List<Brick> capturedBricks = brickCaptor.getAllValues();
        assertEquals(capturedBricks.get(0).getPosition().getX(), 0, 0.001);
        assertEquals(capturedBricks.get(0).getPosition().getY(), 0, 0.001);
    }


}
