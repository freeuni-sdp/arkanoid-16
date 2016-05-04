package ge.edu.freeuni.sdp.arkanoid.model.geometry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Meko on 04/05/2016.
 */
public class CircleTest {
    private Circle circle;
    private final int RADIUS = 10;
    private Point circlePoint;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        circlePoint = new Point(100, 100);
        circle = new Circle(RADIUS, circlePoint);
    }

    @Test
    public void testCircleNotOverlap(){
        assertFalse(circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X, circlePoint.Y + 2*RADIUS)))
                || circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X  + 2*RADIUS, circlePoint.Y)))
                || circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X  - 2*RADIUS, circlePoint.Y)))
                || circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X, circlePoint.Y - 2*RADIUS)))
        );
    }

    @Test
    public void testCircleOverlapSameCircle(){
        Circle testCircle = new Circle(RADIUS, circlePoint);
        assert(circle.canOverlap(testCircle));
    }

    @Test
    public void testTwoSameCenteredCirclesOverlap(){
        Circle testCircle = new Circle(RADIUS*2, circlePoint);
        assert(circle.canOverlap(testCircle));
    }


    @Test
    public void testCircleOverlap(){
        assert(circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X, circlePoint.Y + 2*RADIUS-0.0001)))
            && circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X  + 2*RADIUS-0.0001, circlePoint.Y)))
            && circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X  - 2*RADIUS+0.0001, circlePoint.Y)))
            && circle.canOverlap(new Circle(RADIUS, new Point(circlePoint.X, circlePoint.Y - 2*RADIUS+0.0001)))
        );
    }

    @Test
    public void testRectNotOverlap(){
        Rectangle testRect = mock(Rectangle.class);
        when(testRect.canOverlap(circlePoint)).thenReturn(false);
        assertFalse(circle.canOverlap(testRect));
    }

    @Test
    public void testRectOverlap(){
        Rectangle testRect = mock(Rectangle.class);
        when(testRect.canOverlap(circlePoint)).thenReturn(true);
        assert(circle.canOverlap(testRect));
    }

    @Test
    public void testOtherShapeOverlap(){
        Shape otherShape = mock(Shape.class);
        assertFalse(circle.canOverlap(otherShape));
    }



}