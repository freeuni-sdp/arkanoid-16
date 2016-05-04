package ge.edu.freeuni.sdp.arkanoid.model.geometry;

/**
 * Created by Koko on 03.05.2016.
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {


    @Test
    public void init() {
        Point p = new Point(1,2);
        Point q = new Point(p);
        assertEquals(1, p.X, 0);
        assertEquals(2, p.Y, 0);
        assertEquals(1, q.X, 0);
        assertEquals(2, q.Y, 0);
    }

    @Test
    public void testGetDistance(){
        Point p = new Point(1,1);
        Point other = new Point(4,5);
        double distance = p.getDistance(other);
        assertEquals(5,distance,0);
    }

    @Test
    public void testAdd(){
        Point p = new Point(1,2);
        Point q = new Point(3,0);
        p = p.add(q);
        assertEquals(4, p.X, 0);
        assertEquals(2, p.Y, 0);
    }


    @Test
    public void testMirrorY() {
        Point point =  new Point(1, 2);
        Point mirroredY = point.mirrorY();
        assertEquals(mirroredY.X,1,0);
        assertEquals(mirroredY.Y,-2,0);
    }

    @Test
    public void testMirrorX() {
        Point point =  new Point(2, 3);
        Point mirroredX = point.mirrorX();
        assertEquals(mirroredX.X, -2, 0);
        assertEquals(mirroredX.Y, 3, 0);
    }

    @Test
    public void testMultiply() {
        Point point = new Point(2, 3);
        Point other = new Point(1.5, 2);
        Point mult = point.multiply(other);
        assertEquals(mult.X, 3 , 0);
        assertEquals(mult.Y, 6 , 0);
    }

    @Test
    public void testToGridIndex() {
        Point p = new Point(1.4, 2.6);
        GridIndex gr = p.toGridIndex();
        assertEquals(gr.X,1,0);
        assertEquals(gr.Y,2,0);
    }

    @Test
    public void testSmoothen(){
        Point p = new Point(2.8, 4.9);
        Point smooth1 = p.smoothen(0.6);
        Point smooth2 = p.smoothen(0.4);
        assertEquals(smooth1.X, 2.5, 0.1);
        assertEquals(smooth1.Y, 4.5, 0.1);
        assertEquals(smooth2.X,p.X, 0.1);
        assertEquals(smooth2.Y,p.Y, 0.1);
    }

    @Test
    public void testEquals(){
        Point p = new Point(1,2);
        Size size = new Size(3,3);
        Point a = new Point(1,1);
        Point same = new Point(1,2);
        Point b = new Point(3,4);

        assertTrue(p.equals(p));
        assertFalse(p.equals(null));
        assertFalse(p.equals(size));
        assertFalse(p.equals(a));
        assertTrue(p.equals(same));
        assertFalse(p.equals(b));
    }

    @Test
    public void testHashCode(){
        Point p = new Point(1,2);
        int hc = p.hashCode();
        assertEquals(hc,2082472898,0);
    }
}
