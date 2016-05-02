package ge.edu.freeuni.sdp.arkanoid.model.geometry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RectangleTest {

    @Mock Point point;
    @Mock Size size;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBottomRightCorner() {
        Point upperLeft = new Point(0, 0);
        Size rectSize = new Size(1, 2);

        Rectangle rectangle = new Rectangle(upperLeft, rectSize);
        Point bottomRight = new Point(upperLeft.X + rectSize.getWidth(), upperLeft.Y + rectSize.getHeight());

        assertEquals(bottomRight, rectangle.getBottomRight());
    }

}
