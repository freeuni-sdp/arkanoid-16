package ge.edu.freeuni.sdp.arkanoid.model;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Rakhdakh on 5/4/2016.
 */
public class NormalBrickTest {

    @Mock Capsule capsule;
    @Mock Point position;
    private BrickColor color;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        color = BrickColor.Gray;
    }

    @Test
    public void is_color_right(){
        NormalBrick brick = new NormalBrick(position,color,capsule);
        assertEquals(brick.getColor(),color);
    }

    @Test
    public void Brick_is_the_kind(){
        NormalBrick brick = new NormalBrick(position,color,capsule);
        GobjKind kind = brick.getKind();
        assertEquals(GobjKind.Brick,kind);
    }

    @Test
    public void shape_matters(){
        NormalBrick brick = new NormalBrick(position,color,capsule);
        assertTrue(brick.getShape().getClass() == Rectangle.class);
    }

    @Test
    public void is_alive(){
        NormalBrick brick = new NormalBrick(position,color,capsule);
        assertTrue(brick.isAlive());
    }

    @Test
    public void brick_is_killable(){
        NormalBrick brick = new NormalBrick(position,color,capsule);
        assertTrue(brick.isKillable());
    }

    @Test
    public void normal_ball_kills_normal_brick_wow(){
        Ball ball = mock(Ball.class);
        NormalBrick brick = new NormalBrick(position,color,capsule);
        brick.interact(ball);
        assertFalse(brick.isAlive());
    }

    @Test
    public void golden_ball_doesnt_simply_die() {
        Ball ball = mock(Ball.class);
        NormalBrick brick = new NormalBrick(position, BrickColor.Gold, capsule);
        brick.interact(ball);
        assertTrue(brick.isAlive());
    }
}
