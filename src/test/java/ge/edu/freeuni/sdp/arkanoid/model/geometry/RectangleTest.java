package ge.edu.freeuni.sdp.arkanoid.model.geometry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RectangleTest {

    @Mock Size size;
    private Point position;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        position = new Point(0,0);
        when(size.getWidth()).thenReturn(2);
        when(size.getHeight()).thenReturn(3);
    }

    @Test
    public void testBottomRightCorner() {
        Rectangle target = new Rectangle(position, size);
        Point bottomRight = target.getBottomRight();

        assertEquals(position.X + size.getWidth(), bottomRight.X, 0.0001);
        assertEquals(position.Y + size.getHeight(), bottomRight.Y, 0.0001);
    }

    @Test
    public void testOverlapWithPointInside() {
        Point pointInside = new Point((position.X + size.getWidth())/2, (position.Y + size.getHeight()/2));

        Rectangle target = new Rectangle(position, size);

        assertTrue(target.canOverlap(pointInside));
    }

    @Test
    public void testOverlapAtBorder() {
        Point pointInside = new Point(position.X + size.getWidth(), position.Y + size.getHeight());

        Rectangle target = new Rectangle(position, size);

        assertTrue(target.canOverlap(pointInside));
    }

    @Test
    public void testNoOverlap() {
        Point pointInside = new Point(position.X + size.getWidth() + 10, position.Y + size.getHeight() + 10);

        Rectangle target = new Rectangle(position, size);

        assertFalse(target.canOverlap(pointInside));
    }

    @Test
    public void testCircleOverlap() {
        Circle circle = mock(Circle.class);

        Rectangle target = new Rectangle(position, size);
        when(circle.canOverlap(target)).thenReturn(true);

        assertTrue(target.canOverlap(circle));
    }

    @Test
    public void testCircleNotOverlap() {
        Circle circle = mock(Circle.class);

        Rectangle target = new Rectangle(position, size);
        when(circle.canOverlap(target)).thenReturn(false);

        assertFalse(target.canOverlap(circle));
    }
}
