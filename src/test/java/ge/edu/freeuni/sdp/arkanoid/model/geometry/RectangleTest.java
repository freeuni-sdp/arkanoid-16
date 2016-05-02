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
    private Point targetCenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        position = new Point(0,0);
        when(size.getWidth()).thenReturn(2);
        when(size.getHeight()).thenReturn(3);
        targetCenter = new Point(position.X + size.getWidth()/2.0, position.Y + size.getHeight()/2.0);
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
        Rectangle target = new Rectangle(position, size);

        assertTrue(target.canOverlap(targetCenter));
    }

    @Test
    public void testOverlapAtBorder() {
        Point pointInside = new Point(position.X + size.getWidth(), position.Y + size.getHeight());
        Rectangle target = new Rectangle(position, size);

        assertTrue(target.canOverlap(pointInside));
    }

    @Test
    public void testNoOverlap() {
        Point pointOutside = new Point(position.X + size.getWidth() + 10, position.Y + size.getHeight() + 10);
        Rectangle target = new Rectangle(position, size);

        assertFalse(target.canOverlap(pointOutside));
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

    @Test
    public void testRectangleOverlapsFromTop() {
        Rectangle otherRect = mock(Rectangle.class);
        when(otherRect.getPosition()).thenReturn(new Point(position.X - 1, position.Y - 1));
        when(otherRect.getBottomRight()).thenReturn(targetCenter);

        Rectangle target = new Rectangle(position, size);

        assertTrue(target.canOverlap(otherRect));
    }

    @Test
    public void testRectangleOverlapsFromBottom() {
        Rectangle otherRect = mock(Rectangle.class);
        when(otherRect.getPosition()).thenReturn(targetCenter);
        Point pointOutside = new Point(position.X + size.getWidth() + 10, position.Y + size.getHeight() + 10);
        when(otherRect.getBottomRight()).thenReturn(pointOutside);

        Rectangle target = new Rectangle(position, size);

        assertTrue(target.canOverlap(otherRect));
    }

    @Test
    public void testRectangleNotOverlap() {
        Rectangle otherRect = mock(Rectangle.class);
        Point pointOutside1 = new Point(position.X + size.getWidth() + 10, position.Y + size.getHeight() + 10);
        Point pointOutside2 = new Point(position.X + size.getWidth() + 20, position.Y + size.getHeight() + 20);
        when(otherRect.getPosition()).thenReturn(pointOutside1);
        when(otherRect.getBottomRight()).thenReturn(pointOutside2);

        Rectangle target = new Rectangle(position, size);

        assertFalse(target.canOverlap(otherRect));
    }
}
