package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class BombBrickTest {

    @Mock Capsule capsule;
    @Mock Point point;
    @Mock Room room;
    private BrickColor color;
    private List<Gobj> neighbours;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        color = BrickColor.Red;
        neighbours = new ArrayList<>();
    }

    @Test
    public void target_can_be_killed() {
        BombBrick target = new BombBrick(point, color, capsule, 0, room);
        assertTrue(target.isKillable());
    }

    @Test
    public void kind_is_Brick() {
        BombBrick target = new BombBrick(point, color, capsule, 0, room);
        GobjKind kind = target.getKind();
        assertEquals(GobjKind.Brick, kind);
    }

    @Test
    public void ball_kills_brick() {
        when(room.getGobjs()).thenReturn(Collections.emptySet());
        Ball ball = mock(Ball.class);
        BombBrick target = new BombBrick(point, color, capsule, 1, room);
        target.interact(ball);
        assertFalse(target.isAlive());
    }

    @Test
    public void explosion_kills_close_neighbours() {
        Ball ball = mock(Ball.class);

        Point p = new Point(0, 0);
        storeNeighbour(p);
        positionNeighbours();

        BombBrick brick = new BombBrick(p, color, capsule, 1, room);
        brick.interact(ball);
        verify(neighbours.get(0)).interact(ball);
    }

    private void storeNeighbour(Point p) {
        Brick other = mock(Brick.class);
        when(other.isAlive()).thenReturn(true);
        when(other.getPosition()).thenReturn(p);
        neighbours.add(other);
    }

    private void positionNeighbours() {
        when(room.getGobjs()).thenReturn(new HashSet<>(neighbours));
    }
}