package ge.edu.freeuni.sdp.arkanoid.model.geometry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class RectangleTest {

    @Mock
    Point point;
    @Mock Size size;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPositionSetting() {
        Point positionSet = new Point(1,123);

        Rectangle rectangle = new Rectangle(positionSet, size);
        Point positionGot = rectangle.getPosition();

        assertEquals(positionGot, positionSet);
    }
}
