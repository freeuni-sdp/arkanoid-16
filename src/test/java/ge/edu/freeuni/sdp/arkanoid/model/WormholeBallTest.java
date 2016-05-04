package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Babdus on 5/4/16.
 */
public class WormholeBallTest {

    @Mock ArrayList<PortalBrick> portalBricks;
    @Mock Capsule capsule;
    @Mock Point point;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void kind_is_ball() {
        WormholeBall target = new WormholeBall(portalBricks);
        GobjKind kind = target.getKind();
        assertEquals(GobjKind.Ball, kind);
    }

    @Test
    public void ball_teleports_to_other_portalbrick() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(10, 10);
        PortalBrick pBrick1 = new PortalBrick(point1, BrickColor.Blue, capsule);
        PortalBrick pBrick2 = new PortalBrick(point2, BrickColor.Blue, capsule);
        ArrayList<PortalBrick> pBricks = new ArrayList<PortalBrick>();
        pBricks.add(pBrick1);
        pBricks.add(pBrick2);
        WormholeBall target = new WormholeBall(pBricks);
        target.interact(pBrick1);
        assertEquals(point2, target.getPosition());
    }

    @Test
    public void is_portlBricks_alive_after_interact() {
        PortalBrick pBrick1 = new PortalBrick(point, BrickColor.Blue, capsule);
        PortalBrick pBrick2 = new PortalBrick(point, BrickColor.Blue, capsule);
        ArrayList<PortalBrick> pBricks = new ArrayList<PortalBrick>();
        pBricks.add(pBrick1);
        pBricks.add(pBrick2);
        WormholeBall target = new WormholeBall(pBricks);
        target.interact(pBrick1);
        assertEquals(pBrick1.isAlive(), true);
        assertEquals(pBrick2.isAlive(), true);
    }

    @Test
    public void is_interact_not_called_on_other_portalBrick() {
        PortalBrick pBrick1 = mock(PortalBrick.class);
        PortalBrick pBrick2 = mock(PortalBrick.class);
        when(pBrick2.getPosition()).thenReturn(point);
        ArrayList<PortalBrick> pBricks = new ArrayList<PortalBrick>();
        pBricks.add(pBrick1);
        pBricks.add(pBrick2);
        WormholeBall target = new WormholeBall(pBricks);
        target.interact(pBrick1);
        verify(pBrick2, never()).interact(target);
    }
}
