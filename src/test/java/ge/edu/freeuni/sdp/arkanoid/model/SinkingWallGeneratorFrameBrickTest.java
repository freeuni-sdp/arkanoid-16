package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static ge.edu.freeuni.sdp.arkanoid.model.SinkingWallGeneratorFrameBrick.getSinkingBrickWidth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SinkingWallGeneratorFrameBrickTest {
    private static int TEST_W = 100;
    private static int TEST_H = 100;

    @Mock Point position;
    @Mock Room room;
    @Mock Size size;
    @Mock Configuration mConfiguration;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(mConfiguration.getSize()).thenReturn(new Size(TEST_W, TEST_H));
        Configuration.setInstanceForTesting(mConfiguration);
    }


    @Test
    public void generator_is_alive(){
        SinkingWallGeneratorFrameBrick topFrame = new SinkingWallGeneratorFrameBrick(position, size, room);
        assertTrue(topFrame.isAlive());
    }

    @Test
    public void generator_is_unkillable(){
        SinkingWallGeneratorFrameBrick topFrame = new SinkingWallGeneratorFrameBrick(position, size, room);
        assertFalse(topFrame.isKillable());
    }

    @Test
    public void generator_called_at_least_once(){
        SinkingWallGeneratorFrameBrick topFrame = new SinkingWallGeneratorFrameBrick(position, size, room);
        topFrame.move();
        verify(room, atLeast(1)).add(any());
    }

    @Test
    public void generator_called_right_brick(){
        SinkingWallGeneratorFrameBrick topFrame = new SinkingWallGeneratorFrameBrick(position, size, room);
        topFrame.move();
        ArgumentCaptor<Brick> brickCaptor = ArgumentCaptor.forClass(Brick.class);
        verify(room, atLeast(1)).add(brickCaptor.capture());

        List<Brick> capturedBricks = brickCaptor.getAllValues();
        assertEquals(capturedBricks.get(0).getClass(), SinkingBrick.class);
    }

    @Test
    public void generator_called_right_amount(){
        SinkingWallGeneratorFrameBrick topFrame = new SinkingWallGeneratorFrameBrick(position, size, room);
        topFrame.move();
        verify(room, times(TEST_W/getSinkingBrickWidth())).add(any());
    }

    @Test
    public void generator_called_right_coords(){
        SinkingWallGeneratorFrameBrick topFrame = new SinkingWallGeneratorFrameBrick(position, size, room);
        topFrame.move();
        ArgumentCaptor<Brick> brickCaptor = ArgumentCaptor.forClass(Brick.class);
        verify(room, times(TEST_W/getSinkingBrickWidth())).add(brickCaptor.capture());

        List<Brick> capturedBricks = brickCaptor.getAllValues();

        for(int i = 0; i<capturedBricks.size(); i++) {
            assertEquals(capturedBricks.get(i).getPosition().getX(), i*getSinkingBrickWidth(), 0.001);
            assertEquals(capturedBricks.get(i).getPosition().getY(), 0, 0.001);
        }
    }


}
